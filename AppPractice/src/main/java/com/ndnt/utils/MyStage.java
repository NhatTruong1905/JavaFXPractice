/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import com.ndnt.apppractice.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyStage {

    private static MyStage instance;
    private final Stage stage;
    private static Scene scene;

    private MyStage() {
        stage = new Stage();
        stage.setTitle("Practice App");
    }

    public static MyStage getInstance() {
        if (instance == null) {
            instance = new MyStage();
        }
        return instance;
    }

    public void showStage(String fxml) throws IOException {
        scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
