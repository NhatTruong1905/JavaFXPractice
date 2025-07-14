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
import com.ndnt.services.CategoryServices;
import com.ndnt.services.LevelServices;
import com.ndnt.services.QuestionServices;
import com.ndnt.utils.MyAlert;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private static final CategoryServices catServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();
    private static final QuestionServices questionServices = new QuestionServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            this.cbCates.setItems(FXCollections.observableArrayList(catServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableArrayList(levelServices.getLevels()));

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
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
            questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMyAlert("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMyAlert("Thêm không thành công, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMyAlert("Dữ liệu không hợp lệ!");
        }
    }
}
