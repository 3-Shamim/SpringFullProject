package com.learningstuff.librarymanagentsystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer bookId;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String bookName;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String author;
    @NotNull
    @Size(min = 1, message = "Field must not be empty!")
    private String edition;
    @NotNull(message = "Field must not be empty!")
    @Min(0)
    private Integer quantity;
    private boolean activity;
    public Book()
    {

    }

    public Book(Integer bookId, String bookName, String author, String edition, Integer quantity, boolean activity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.edition = edition;
        this.quantity = quantity;
        this.activity = activity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", quantity=" + quantity +
                ", activity=" + activity +
                '}';
    }
}
