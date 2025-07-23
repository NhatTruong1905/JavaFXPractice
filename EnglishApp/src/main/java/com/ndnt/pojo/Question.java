/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Question {

    private int id;
    private String content;
    private Category category;
    private List<Choice> choices;

    private Question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.choices = b.choices;
        this.category = b.category;
    }

    public static class Builder {

        private int id;
        private String content;
        private Category category;
        private List<Choice> choices = new ArrayList<>();

        public Builder(String content, Category category) {
            this.content = content;
            this.category = category;
        }

        public Builder addChoice(Choice c) {
            this.choices.add(c);
            return this;
        }

        public Builder addAllChoice(List<Choice> choices) {
            this.choices.addAll(choices);
            return this;
        }

        public Question build() {
            return new Question(this);
        }
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

}
