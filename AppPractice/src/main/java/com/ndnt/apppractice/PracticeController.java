/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndnt.apppractice;

import com.ndnt.pojo.Question;
import com.ndnt.utils.Configs;
import com.ndnt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    TextField txtNum;
    @FXML
    Text txtContent;
    @FXML
    Text txtResult;
    @FXML
    VBox vboxChoice;

    private List<Question> questions;
    private int currentQuestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleStart(ActionEvent action) throws SQLException, ClassNotFoundException {
        try {
            int num = Integer.parseInt(txtNum.getText());

            questions = Configs.questionServices.getQuestions(num);

            this.currentQuestion = 0;
            this.loadQuestion();
        } catch (NumberFormatException e) {
            MyAlert.getInstance().showMyAlert("Vui lòng nhập số câu hỏi hợp lệ");
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(currentQuestion);
        this.txtContent.setText(q.getContent());

        ToggleGroup tg = new ToggleGroup();
        vboxChoice.getChildren().clear();
        for (var c : q.getChoices()) {
            RadioButton rdo = new RadioButton(c.getContent());
            rdo.setToggleGroup(tg);

            vboxChoice.getChildren().add(rdo);
        }
    }

    public void handleNext(ActionEvent action) {
        if (this.currentQuestion < this.questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }

    public void handleCheck(ActionEvent action) {
        Question q = this.questions.get(currentQuestion);

        for (int i = 0; i < q.getChoices().size() - 1; i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton r = (RadioButton) vboxChoice.getChildren().get(i);
                if (r.isSelected()) {
                    this.txtResult.setText("CHÍNH XÁC!");
                    this.txtResult.setStyle("-fx-fill: blue");
                } else {
                    this.txtResult.setText("SAI RÙI HEHE ^^!");
                    this.txtResult.setStyle("-fx-fill: red");
                }
            }
        }
    }

}
