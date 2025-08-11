package com.samwellstore.librarymanagement.services.impl;

import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.Repositories.BookRepository;
import com.samwellstore.librarymanagement.entities.Book;
import com.samwellstore.librarymanagement.services.BookService;
import com.samwellstore.librarymanagement.utils.mapper.impl.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    final BookMapper bookMapper;
    final BookRepository bookRepository;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = bookMapper.mapFrom(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapTo(savedBook);
    }

    @Override
    public BookDTO getBookByISBN(BookDTO bookDTO) {
        Book book = bookMapper.mapFrom(bookDTO);
        Book foundBook = bookRepository.findByIsbn(book.getIsbn())
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + book.getIsbn()));
        return bookMapper.mapTo(foundBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long bookId) {
            Book existingBook = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
            updateBookFields(existingBook, bookDTO);
            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.mapTo(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        return bookMapper.mapTo(book);
    }





    private void updateBookFields(Book book, BookDTO bookDTO) {
        Optional.ofNullable(bookDTO.getTitle()).ifPresent(book::setTitle);
        Optional.ofNullable(bookDTO.getAuthor()).ifPresent(book::setAuthor);
        Optional.ofNullable(bookDTO.getIsbn()).ifPresent(book::setIsbn);
        if (bookDTO.getTotalCopies() > 0) {
            book.setTotalCopies(bookDTO.getTotalCopies());
            book.setAvailableCopies(Math.min(book.getAvailableCopies(), bookDTO.getTotalCopies()));
        }
        if (bookDTO.getAvailableCopies() >= 0) {
            book.setAvailableCopies(Math.min(bookDTO.getAvailableCopies(), book.getTotalCopies()));
        }
    }

}
