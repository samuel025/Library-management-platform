package com.samwellstore.librarymanagement.DTOs.BookDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
}
