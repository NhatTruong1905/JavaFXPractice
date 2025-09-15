/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.user;

import com.ndnt.Utils.JdbcConnector;
import com.ndnt.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class UpdateUserService {

    public void addUser(User u) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("INSERT INTO user(name, phone, email, address, image, groupcontact_id) VALUES(?, ?, ?, ?, ?, ?)");
        stm.setString(1, u.getName());
        stm.setString(2, u.getPhone());
        stm.setString(3, u.getEmail());
        stm.setString(4, u.getAddress());
        stm.setString(5, u.getImage());
        stm.setInt(6, u.getGroupName().getId());
        stm.executeUpdate();
    }
    
}
