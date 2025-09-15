/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.Utils.JdbcConnector;
import com.ndnt.pojo.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class GroupServices extends BaseServices<Group> {

    @Override
    public PreparedStatement getStm(Connection conn) throws Exception {
        return conn.prepareCall("SELECT * FROM groupcontact");
    }

    @Override
    public List<Group> getResult(ResultSet rs) throws Exception {
        List<Group> groups = new ArrayList<>();
        while (rs.next()) {
            groups.add(new Group(rs.getInt("id"), rs.getString("name")));
        }

        return groups;
    }
}
