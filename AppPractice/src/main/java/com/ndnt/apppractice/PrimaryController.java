package com.ndnt.apppractice;

import com.ndnt.utils.MyAlert;
import com.ndnt.utils.MyStage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class PrimaryController {

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
