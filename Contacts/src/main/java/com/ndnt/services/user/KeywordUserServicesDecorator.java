/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.user;

import java.util.List;

/**
 *
 * @author admin
 */
public class KeywordUserServicesDecorator extends UserDecorator {

    private String keyword;

    public KeywordUserServicesDecorator(BaseUserServices decorator, String kw) {
        super(decorator);
        this.keyword = kw;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND name like concat('%',?,'%')";
        params.add(this.keyword);
        return sql;
    }

}
