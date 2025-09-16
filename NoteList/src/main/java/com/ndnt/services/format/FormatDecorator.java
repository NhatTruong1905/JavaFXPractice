/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.format;

/**
 *
 * @author admin
 */
public abstract class FormatDecorator extends BaseFormatService {

    protected BaseFormatService decorator;

    public FormatDecorator(BaseFormatService decorator) {
        this.decorator = decorator;
    }

}
