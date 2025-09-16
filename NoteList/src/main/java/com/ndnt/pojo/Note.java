/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.pojo;

import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class Note {

    private int id;
    private String title;
    private String content;
    private String date;
    private Tag tag;

    public Note(int id, String title, String content, String dated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = dated;
    }

    public Note(String title, String content, String date, Tag tag) throws Exception {
        if (title.isEmpty() || content.isEmpty() || tag == null) {
            throw new Exception("Invalid Data");
        }
        this.title = title;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDated() {
        return date;
    }

    public void setDated(String dated) {
        this.date = dated;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
