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
    private Alert alert;

    private MyAlert() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setHeaderText("NOTE LIST");
    }

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }

        return instance;
    }

    public void show(String msg) {
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
}
