/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Note;
import com.ndnt.utils.DatabaseConnection;
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
public class NoteServices extends BaseServices<Note> {

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM note");
    }

    @Override
    public List<Note> getResult(ResultSet rs) throws SQLException {
        List<Note> notes = new ArrayList<>();
        while (rs.next()) {
            notes.add(new Note(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("date")));
        }

        return notes;
    }

    public void addNote(Note n) throws SQLException {
        Connection conn = DatabaseConnection.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("INSERT INTO note(title, content, date, tag_id) VALUES (?, ?, ?, ?)");
        stm.setString(1, n.getTitle());
        stm.setString(2, n.getContent());
        stm.setString(3, n.getDated());
        stm.setInt(4, n.getTag().getId());

        stm.executeUpdate();
    }

    public boolean deleteNote(Note n) throws SQLException {
        Connection conn = DatabaseConnection.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("DELETE FROM note WHERE id=?");
        stm.setInt(1, n.getId());

        return stm.executeUpdate() > 0;
    }

    public boolean updateNote(Note n) throws SQLException {
        Connection conn = DatabaseConnection.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("UPDATE note SET title=?, content=?, date=?, tag_id=? WHERE id=?");
        stm.setString(1, n.getTitle());
        stm.setString(2, n.getContent());
        stm.setString(3, n.getDated());
        stm.setInt(4, n.getTag().getId());
        stm.setInt(5, n.getId());

        return stm.executeUpdate() > 0;
    }
}
