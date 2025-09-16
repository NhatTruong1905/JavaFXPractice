/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TagServices extends BaseServices<Tag> {

    @Override
    public PreparedStatement getStm(Connection conn) throws Exception {
        return conn.prepareCall("SELECT * FROM tag");
    }

    @Override
    public List<Tag> getResult(ResultSet rs) throws Exception {
        List<Tag> tags = new ArrayList<>();
        while (rs.next()) {
            tags.add(new Tag(rs.getInt("id"), rs.getString("content")));
        }

        return tags;
    }
}
