/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.format;

import com.ndnt.pojo.Note;

/**
 *
 * @author admin
 */
public class BoldFormatDecorator extends FormatDecorator {

    public BoldFormatDecorator(BaseFormatService decorator) {
        super(decorator);
    }

    @Override
    public String getFXML(Note n) {
        return "<b>" + this.decorator.getFXML(n) + "</b>";
    }

}
