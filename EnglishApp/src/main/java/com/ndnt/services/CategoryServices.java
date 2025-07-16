/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Category;
import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryServices {

    private Connection conn;

    public List<Category> getCates() throws SQLException {
        conn = JdbcConnector.getInstance().connect();

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");

        List<Category> cates = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            cates.add(new Category(id, name));
        }

        return cates;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
