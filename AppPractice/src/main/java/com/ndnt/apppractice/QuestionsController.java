/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndnt.apppractice;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import com.ndnt.pojo.Category;
import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Level;
import com.ndnt.pojo.Question;
import com.ndnt.utils.Configs;
import com.ndnt.utils.MyAlert;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    @FXML
    ToggleGroup toggleChoice;
    @FXML
    TextArea txtContent;
    @FXML
    private VBox vboxChoices;
    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private TableView tbQuestion;
    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableArrayList(Configs.catServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableArrayList(Configs.levelServices.getLevels()));

            this.loadColumns();
            this.tbQuestion.setItems(FXCollections.observableList(Configs.questionServices.getQuestions()));
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }

        this.txtSearch.textProperty().addListener(c -> {
            try {
                this.tbQuestion.getItems().clear();
                this.tbQuestion.setItems(FXCollections.observableList(Configs.questionServices.getQuestions(txtSearch.getText())));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    public void addChoice(ActionEvent action) {
        HBox h = new HBox();
        h.getStyleClass().add("Main"); // Căn đúng như các hbox trên giao diện

        RadioButton rdo = new RadioButton();
        rdo.setToggleGroup(toggleChoice); // Gom nhóm chỉ 1 đáp án đúng (Set toggle group)
        TextField txf = new TextField();

        h.getChildren().addAll(rdo, txf);
        this.vboxChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent action) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());

            for (var c : this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;

                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());

                b.addChoice(choice);
            }

            // Question q = b.build();
            Configs.questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMyAlert("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMyAlert("Thêm không thành công, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMyAlert("Dữ liệu không hợp lệ!");
        }
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(c -> {
            TableCell cell = new TableCell();

            Button btn = new Button("Xóa");
            btn.setOnAction(e -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMyAlert("Xóa câu hỏi thì các đáp án cũng bị xóa theo. Bạn chắc chắn không?", Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get().equals(ButtonType.OK)) {
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        if (Configs.questionServices.deleteQuestion(q.getId()) == true) {
                            this.tbQuestion.getItems().remove(q);
                            MyAlert.getInstance().showMyAlert("Xóa câu hỏi thành công!", Alert.AlertType.INFORMATION);
                        } else {
                            MyAlert.getInstance().showMyAlert("Xóa câu hỏi thất bại!", Alert.AlertType.WARNING);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        MyAlert.getInstance().showMyAlert("Hệ thống bị lỗi, lý do " + ex.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            });

            cell.setGraphic(btn);

            return cell;

        });

        this.tbQuestion.getColumns().addAll(colId, colContent, colAction);
    }
}
