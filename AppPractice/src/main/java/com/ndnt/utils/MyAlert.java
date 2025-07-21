/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class MyAlert {

    private static MyAlert instance;
    private final Alert alert;

    private MyAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Practice App");
    }

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }

    public void showMyAlert(String noiDung) {
        alert.setContentText(noiDung);
        alert.showAndWait();
    }

    public Optional<ButtonType> showMyAlert(String noiDung, Alert.AlertType type) {
        alert.setContentText(noiDung);
        alert.setAlertType(type);

        return alert.showAndWait();
    }
}
