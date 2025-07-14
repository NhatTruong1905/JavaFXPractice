/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.themes;

import com.ndnt.apppractice.App;

/**
 *
 * @author admin
 */
public class DefaultThemeFactory implements ThemeFactory {

    @Override
    public String getStylesheet() {
        return App.class.getResource("style.css").toExternalForm();
    }
}
