/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseServices<T> {

    public abstract PreparedStatement getStm(Connection conn) throws Exception;

    public abstract List<T> getResult(ResultSet rs) throws Exception;

    public List<T> list() throws SQLException, Exception {
        Connection conn = DatabaseConnection.getInstance().connect();

        PreparedStatement stm = this.getStm(conn);

        return getResult(stm.executeQuery());
    }
}
