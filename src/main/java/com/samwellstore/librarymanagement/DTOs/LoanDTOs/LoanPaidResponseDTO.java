package com.samwellstore.librarymanagement.DTOs.LoanDTOs;


import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanPaidResponseDTO {
    private Long id;
    private String loanDate;
    private BookDTO book;
    private LocalDate returnDate;
    private boolean returned;
}
