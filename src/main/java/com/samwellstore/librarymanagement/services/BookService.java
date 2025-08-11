package com.samwellstore.librarymanagement.services;

import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.entities.Book;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    BookDTO getBookByISBN(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(BookDTO bookDTO, Long bookId);
    void deleteBook(Long bookId);
    BookDTO getBookById(Long bookId);
}
