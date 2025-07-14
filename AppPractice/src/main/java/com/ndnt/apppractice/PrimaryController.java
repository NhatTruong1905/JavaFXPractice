package com.ndnt.apppractice;

import com.ndnt.themes.DarkThemeFactory;
import com.ndnt.themes.Theme;
import com.ndnt.themes.ThemeManager;
import com.ndnt.utils.MyAlert;
import com.ndnt.utils.MyStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {

    @FXML
    ComboBox<Theme> cbThmes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThmes.setItems(FXCollections.observableArrayList(Theme.values()));
    }

    public void themeController(ActionEvent action) {
        this.cbThmes.getSelectionModel().getSelectedItem().updateTheme(this.cbThmes.getScene());
    }

    public void questionController(ActionEvent action) throws IOException {
        MyStage.getInstance().showStage("questions.fxml");
    }

    public void practiceController(ActionEvent action) {
        MyAlert.getInstance().showMyAlert("Coming soon hihi^^!");
    }

    public void testController(ActionEvent action) {
        MyAlert.getInstance().showMyAlert("Coming soon hihi^^!");
    }

    public void registerController(ActionEvent action) {
        MyAlert.getInstance().showMyAlert("Coming soon hihi^^!");
    }

    public void logInController(ActionEvent action) {
        MyAlert.getInstance().showMyAlert("Coming soon hihi^^!");
    }

}
