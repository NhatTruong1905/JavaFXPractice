/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.pojo;

/**
 *
 * @author admin
 */
public class User {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String image;
    private Group groupName;

    private User(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.address = b.address;
        this.phone = b.phone;
        this.email = b.email;
        this.image = b.image;
        this.groupName = b.groupName;
    }

    public static class Builder {

        private int id;
        private String name;
        private String phone;
        private String email;
        private String address;
        private String image;
        private Group groupName;

        public Builder(String name, String phone, Group g) throws Exception {
            if (name.isEmpty() || phone.isEmpty() || g == null) {
                throw new Exception("Invalid Data");
            }
            this.name = name;
            this.phone = phone;
            this.groupName = g;
        }

        public Builder(int id, String name, String phone, String image) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.image = image;
        }

        public Builder addAdress(String add) {
            this.address = add;
            return this;
        }

        public Builder addImage(String i) {
            this.image = i;
            return this;
        }

        public Builder addEmail(String e) {
            this.email = e;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public void setGroupName(Group groupName) {
        this.groupName = groupName;
    }

    public Group getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
