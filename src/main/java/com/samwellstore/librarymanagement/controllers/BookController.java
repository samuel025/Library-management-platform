package com.samwellstore.librarymanagement.controllers;


import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.Repositories.BookRepository;
import com.samwellstore.librarymanagement.entities.Book;
import com.samwellstore.librarymanagement.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    final BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/book/{bookId}")
    public BookDTO getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }


}
