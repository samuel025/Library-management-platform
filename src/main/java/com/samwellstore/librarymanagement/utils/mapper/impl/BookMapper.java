package com.samwellstore.librarymanagement.utils.mapper.impl;

import com.samwellstore.librarymanagement.DTOs.BookDTOs.BookDTO;
import com.samwellstore.librarymanagement.entities.Book;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class BookMapper implements Mapper<Book, BookDTO> {
    final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO mapTo(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
