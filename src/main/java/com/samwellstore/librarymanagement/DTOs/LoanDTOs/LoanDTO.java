package com.samwellstore.librarymanagement.DTOs.LoanDTOs;


import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDTO {
    private Long id;
    private String loanDate;
    private BookDTO book;
}
