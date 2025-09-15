/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.user;

import com.ndnt.pojo.User;
import com.ndnt.services.BaseServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseUserServices extends BaseServices<User> {

    public abstract String getSQL(List<Object> params);

    @Override
    public PreparedStatement getStm(Connection conn) throws Exception {
        List<Object> params = new ArrayList<>();
        PreparedStatement stm = conn.prepareCall(this.getSQL(params));
        for (int i = 0; i < params.size(); i++) {
            stm.setObject(i + 1, params.get(i));
        }
        return stm;
    }

    @Override
    public List<User> getResult(ResultSet rs) throws Exception {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User.Builder(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("image")).build());
        }

        return users;
    }

}
