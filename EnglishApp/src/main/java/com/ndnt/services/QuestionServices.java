/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Question;
import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class QuestionServices {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);

        PreparedStatement stm = conn.prepareCall("INSERT INTO question(content, category_id) VALUES(?, ?) ");
        stm.setString(1, q.getContent());
        stm.setInt(2, q.getCategory().getId());

        if (stm.executeUpdate() > 0) {
            int question_id = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                question_id = rs.getInt(1);
            }

            stm = conn.prepareCall("INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)");
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isIs_correct());
                stm.setInt(3, question_id);

                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }

    }
}
