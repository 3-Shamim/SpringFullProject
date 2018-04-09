package com.learningstuff.librarymanagentsystem.Model.Dao;

import com.learningstuff.librarymanagentsystem.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDaoLocal {

    private static List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book(1,"Book1","author1","first",5,true));
        bookList.add(new Book(2,"Book2","author2","first",4,true));
        bookList.add(new Book(3,"Book3","author3","first",6,true));
        bookList.add(new Book(4,"Book4","author4","first",0,false));
        bookList.add(new Book(5,"Book5","author5","first",8,true));
        bookList.add(new Book(6,"Book6","author6","first",1,true));
    }

    public List<Book> getAllBook()
    {
        return bookList;
    }
    public void addBook(Book book)
    {
        bookList.add(book);
    }
    public Book getById(int id)
    {
        Book book = null;
        for (Book b: bookList) {
            if (b.getBookId().equals(id))
            {
                book = b;
            }
        }
        return book;
    }
    public void deleteBookById(Book book)
    {
        bookList.remove(book);
    }
}
