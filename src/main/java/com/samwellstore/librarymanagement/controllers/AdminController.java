package com.samwellstore.librarymanagement.controllers;


import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.services.AdminService;
import com.samwellstore.librarymanagement.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;
    final BookService bookService;

    public AdminController(AdminService adminService, BookService bookService) {
        this.bookService = bookService;
        this.adminService = adminService;
    }

    @PostMapping(path = "/register")
    public SignUpResponseDTO AddAdmin(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return adminService.addAdmin(signUpRequestDTO);
    }

    @PostMapping(path = "/addBook")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PutMapping(path = "/book/{bookId}")
    public ResponseEntity<BookDTO> updateBook (@PathVariable("bookId") Long bookId, @RequestBody BookDTO bookDTO){
        return  ResponseEntity.ok(bookService.updateBook(bookDTO, bookId));
    }

    @DeleteMapping(path = "/book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

}
