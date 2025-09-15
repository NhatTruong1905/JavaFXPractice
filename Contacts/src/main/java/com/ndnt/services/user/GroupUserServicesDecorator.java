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
public class GroupUserServicesDecorator extends UserDecorator {

    private int groupcontactId;

    public GroupUserServicesDecorator(BaseUserServices decorator, int id) {
        super(decorator);
        this.groupcontactId = id;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND groupcontact_id=?";
        params.add(this.groupcontactId);
        return sql;
    }

}
