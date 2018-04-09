package com.learningstuff.librarymanagentsystem.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String userId;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String userName;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String email;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String address;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;
    public User() {
    }

    public User(String userId, String userName, String email, String address, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", book=" + book +
                '}';
    }
}
