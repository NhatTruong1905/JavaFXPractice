/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.Utils;

import javafx.scene.control.Alert;

/**
 *
 * @author admin
 */
public class MyAlert {

    private static MyAlert instance;
    private Alert alert;

    private MyAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contacts");
    }

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }

        return instance;
    }

    public void show(String msg) {
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
