package com.ndnt.englishapp;

import com.ndnt.pojo.Category;
import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Question;
import com.ndnt.utils.Configs;
import com.ndnt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private TextArea txtContent;
    @FXML
    private VBox vboxChoice;
    @FXML
    ToggleGroup toggleChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableArrayList(Configs.categoryServices.getCates()));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addQuestion(ActionEvent action) {
        try {
            Question.Builder b = new Question.Builder(txtContent.getText(),
                    cbCates.getSelectionModel().getSelectedItem());

            for (var c : vboxChoice.getChildren()) {
                HBox h = (HBox) c;
                RadioButton rdo = (RadioButton) h.getChildren().get(0);
                rdo.setToggleGroup(toggleChoice);
                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        rdo.isSelected());

                b.addChoice(choice);
            }

            Configs.questionServices.addQuestion(b.build());
            MyAlert.getInstance().showAlert("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showAlert("Thêm dữ liệu không thành công, lý do " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showAlert("Dữ liệu không hợp lệ!!!");
        }

    }

    public void reset(ActionEvent action) {

    }
}
