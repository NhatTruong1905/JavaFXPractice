/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.user;

/**
 *
 * @author admin
 */
public abstract class UserDecorator extends BaseUserServices {

    protected BaseUserServices decorator;

    public UserDecorator(BaseUserServices decorator) {
        this.decorator = decorator;
    }

}
