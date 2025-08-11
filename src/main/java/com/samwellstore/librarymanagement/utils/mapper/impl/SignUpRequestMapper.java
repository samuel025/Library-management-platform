package com.samwellstore.librarymanagement.utils.mapper.impl;


import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.entities.User;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SignUpRequestMapper implements Mapper<User, SignUpRequestDTO> {
    final ModelMapper modelMapper;

    public SignUpRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SignUpRequestDTO mapTo(User user) {
        return modelMapper.map(user, SignUpRequestDTO.class);
    }

    @Override
    public User mapFrom(SignUpRequestDTO signUpRequestDTO) {
        return modelMapper.map(signUpRequestDTO, User.class);
    }
}
