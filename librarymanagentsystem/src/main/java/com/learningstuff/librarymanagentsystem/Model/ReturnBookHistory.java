package com.learningstuff.librarymanagentsystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ReturnBookHistory {
    @Id
    @GeneratedValue
    private Integer Id;
    private String UserId;
    private String userName;
    private Integer BookId;
    private String bookName;
    private LocalDate localDate;

    public ReturnBookHistory() {
    }

    public ReturnBookHistory(String userId, String userName, Integer bookId, String bookName, LocalDate localDate) {
        UserId = userId;
        this.userName = userName;
        BookId = bookId;
        this.bookName = bookName;
        this.localDate = localDate;
    }

    public Integer getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        this.BookId = bookId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "ReturnBookHistory{" +
                "Id=" + Id +
                ", UserId='" + UserId + '\'' +
                ", userName='" + userName + '\'' +
                ", BookId=" + BookId +
                ", bookName='" + bookName + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
