package com.example.ratelimiter.resources;

import com.example.ratelimiter.dao.BookDao;
import com.example.ratelimiter.exception.TooManyTryException;
import com.example.ratelimiter.model.Book;
import com.example.ratelimiter.model.User;
import com.example.ratelimiter.security.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class Controller {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RateLimiter rateLimiter;

    @GetMapping
    public List<Book> getAllBook(User user) {
        checkRateLimit(user);
        return bookDao.getAll();
    }

    private void checkRateLimit(User user) {
        if(!rateLimiter.checkLimit(user.getName()))
            throw new TooManyTryException("Try later");
    }
}
