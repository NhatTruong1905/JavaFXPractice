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
public class UserServices extends BaseUserServices {

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM user WHERE 1=1";
    }
//
//    public List<User> getUsers(String kw) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM user WHERE name like concat('%',?,'%')");
//        stm.setString(1, kw);
//
//        ResultSet rs = stm.executeQuery();
//
//        List<User> users = new ArrayList<>();
//        while (rs.next()) {
//            users.add(new User.Builder(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("image")).build());
//        }
//
//        return users;
//    }
//
//    public List<User> getUsers(int groupcontact_id) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM user WHERE groupcontact_id=?");
//        stm.setInt(1, groupcontact_id);
//
//        ResultSet rs = stm.executeQuery();
//
//        List<User> users = new ArrayList<>();
//        while (rs.next()) {
//            users.add(new User.Builder(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("image")).build());
//        }
//
//        return users;
//    }

}
