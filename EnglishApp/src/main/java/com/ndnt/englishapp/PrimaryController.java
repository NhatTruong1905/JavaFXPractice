package com.ndnt.englishapp;

import com.ndnt.pojo.Category;
import com.ndnt.services.CategoryServices;
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
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private TextField txtContent;
    @FXML
    private TextField txtA;
    @FXML
    private TextField txtB;
    @FXML
    private TextField txtC;
    @FXML
    private TextField txtD;
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;
    private static final CategoryServices categoryServices = new CategoryServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableArrayList(categoryServices.getCates()));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addQuestion(ActionEvent action) {

    }

    public void reset(ActionEvent action) {

    }
}
