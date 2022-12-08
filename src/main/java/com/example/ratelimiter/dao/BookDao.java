package com.example.ratelimiter.dao;

import com.example.ratelimiter.model.Book;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDao {
    private Map<UUID, Book> db;

    public BookDao() {
        this.db = new HashMap<>();
    }

    public Book saveOrUpdate(Book book) {
        db.put(book.getId(), book);
        return book;
    }

    public List<Book> getAll(){
        return new ArrayList<>(db.values());
    }
}
