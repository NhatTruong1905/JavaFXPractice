/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import javafx.scene.control.Alert;

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
}
