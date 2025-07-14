/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.pojo;

/**
 *
 * @author admin
 */
public class Choice {

    private int id;
    private String content;
    private boolean correct;

    public Choice(int id, String content, boolean is_correct) {
        this.id = id;
        this.content = content;
        this.correct = is_correct;
    }

    public Choice(String content, boolean is_correct) {
        this.content = content;
        this.correct = is_correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean is_correct) {
        this.correct = is_correct;
    }

}
