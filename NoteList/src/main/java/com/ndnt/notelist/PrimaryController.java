package com.ndnt.notelist;

import com.ndnt.pojo.Note;
import com.ndnt.pojo.Tag;
import com.ndnt.services.format.BaseFormatService;
import com.ndnt.services.format.BoldFormatDecorator;
import com.ndnt.services.format.FormatService;
import com.ndnt.services.format.ItalicFormatDecorator;
import com.ndnt.theme.Theme;
import com.ndnt.utils.FlyweightFactory;
import com.ndnt.utils.MyAlert;
import com.ndnt.utils.MyConfig;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;

public class PrimaryController implements Initializable {

    @FXML
    ComboBox<Tag> cbTag;
    @FXML
    TextField txtTitle;
    @FXML
    TextArea txtContent;
    @FXML
    DatePicker dateChoice;
    @FXML
    TableView<Note> tbNote;
    @FXML
    CheckBox ckBold;
    @FXML
    CheckBox ckItalic;
    @FXML
    TextFlow txtFormatNote;
    @FXML
    ComboBox<Theme> cbThemes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbTag.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(MyConfig.tagServices, "tags")));

            this.loadColumns();
            this.tbNote.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(MyConfig.noteService, "notes")));

            this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
        } catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeThemeHandle(ActionEvent action) throws IOException {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }

    public void addHandle(ActionEvent action) {
        try {
            Note n = new Note(txtTitle.getText(), txtContent.getText(), dateChoice.getValue().toString(), cbTag.getSelectionModel().getSelectedItem());

            BaseFormatService s = new FormatService();
            if (ckBold.isSelected()) {
                s = new BoldFormatDecorator(s);
            }
            if (ckItalic.isSelected()) {
                s = new ItalicFormatDecorator(s);
            }
            String formatContent = s.getFXML(n);
            n.setContent(formatContent);

            MyConfig.noteService.addNote(n);
            this.tbNote.getItems().add(n);
            MyAlert.getInstance().show("Thêm ghi chú thành công");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            MyAlert.getInstance().show("Thêm ghi chú thất bại, lý do: " + ex.getMessage());
        }
    }

    private void loadColumns() {
        TableColumn colTitle = new TableColumn("Tiêu đề");
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colTitle.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        colContent.setCellFactory(param -> new TableCell<Note, String>() {
            @Override
            protected void updateItem(String t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null || empty == true) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label contentLabel = new Label();
                    contentLabel.setText(t.replaceAll("<[^>]*>", ""));

                    if (t.contains("<b>") && t.contains("</b>")) {
                        contentLabel.setStyle("-fx-font-weight:bold;");
                    }
                    if (t.contains("<i>") && t.contains("</i>")) {
                        contentLabel.setStyle(contentLabel.getStyle() + "-fx-font-style:italic;");
                    }

                    setGraphic(contentLabel);
                    setText(null);
                }
            }
        });

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(a -> {
            TableCell cell = new TableCell();

            Button btn = new Button("Xóa");
            btn.setOnAction(e -> {
                Optional<ButtonType> t = MyAlert.getInstance().show("Bạn có chắc chắn xóa không ?", Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get() == ButtonType.OK) {
                    Note n = (Note) cell.getTableRow().getItem();
                    try {
                        if (MyConfig.noteService.deleteNote(n) == true) {
                            this.tbNote.getItems().remove(n);
                            MyAlert.getInstance().show("Xóa ghi chú thành công !", Alert.AlertType.INFORMATION);
                        } else {
                            MyAlert.getInstance().show("Xóa ghi chú thất bại !!!", Alert.AlertType.WARNING);
                        }
                    } catch (SQLException ex) {
                        MyAlert.getInstance().show("Dữ liệu không chính xác, lý do: " + ex.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            });

            cell.setGraphic(btn);

            return cell;
        });

        this.tbNote.getColumns().addAll(colTitle, colContent, colAction);
    }

    public void viewSelectedNote(ActionEvent action) {
        Note n = tbNote.getSelectionModel().getSelectedItem();

        if (n == null) {
            MyAlert.getInstance().show("Vui lòng chọn một ghi chú để xem!");
            return;
        }

        txtFormatNote.getChildren().clear();

        Label contentLabel = new Label();

        contentLabel.setText(n.getContent().replaceAll("<[^>]*>", ""));

        String style = "";

        if (n.getContent().contains("<b>") && n.getContent().contains("</b>")) {
            style += "-fx-font-weight: bold;";
        }

        if (n.getContent().contains("<i>") && n.getContent().contains("</i>")) {
            style += "-fx-font-style: italic;";
        }

        contentLabel.setStyle(style);

        txtFormatNote.getChildren().add(contentLabel);
    }

    public void updateNote(ActionEvent action) throws SQLException {
        Note n = this.tbNote.getSelectionModel().getSelectedItem();
        try {
            if (n != null) {
                Optional<ButtonType> t = MyAlert.getInstance().show("Bạn có chắc muốn sửa không ?", Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get() == ButtonType.OK) {
                    n.setContent(txtContent.getText());
                    n.setTitle(txtTitle.getText());
                    n.setDated(dateChoice.getValue().toString());
                    n.setTag(cbTag.getSelectionModel().getSelectedItem());

                    MyConfig.noteService.updateNote(n);
                    MyAlert.getInstance().show("Cập nhập ghi chú thành công", Alert.AlertType.INFORMATION);
                }
            }
        } catch (Exception ex) {
            MyAlert.getInstance().show("Dữ liệu không chính xác, lý do: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
