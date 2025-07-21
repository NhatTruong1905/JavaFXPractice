/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Question;
import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionServices {

    public void addQuestion(Question q) throws ClassNotFoundException, SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false); // Dùng để lưu vào vùng đệm khi thực hiện thành công
        // thì lưu vào database

        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            // Chỉ áp dụng với id tự động tăng 
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }
    }

    public List<Question> getQuestions() throws ClassNotFoundException, SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");
        List<Question> questions = new ArrayList<>();

        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException, ClassNotFoundException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%',  ?,  '%')");
        stm.setString(1, kw);

        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();

        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Choice> getChoices(int question_id) throws SQLException, ClassNotFoundException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, question_id);

        ResultSet rs = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();

        while (rs.next()) {
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }
        return choices;
    }

    public List<Question> getQuestions(int num) throws SQLException, ClassNotFoundException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);

        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();

        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).addAllChoice(this.getChoices(rs.getInt("id"))).build();
            questions.add(q);
        }
        return questions;
    }

    public boolean deleteQuestion(int id) throws SQLException, ClassNotFoundException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, id);

        return stm.executeUpdate() > 0;
    }

}
