package com.samwellstore.librarymanagement.utils.mapper.impl;

import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.entities.User;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class SignUpReponseMapper implements Mapper<User, SignUpResponseDTO> {

    final ModelMapper modelMapper;

    public SignUpReponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SignUpResponseDTO mapTo(User user) {
        return modelMapper.map(user, SignUpResponseDTO.class);
    }

    @Override
    public User mapFrom(SignUpResponseDTO sIgnUpResponseDTO) {
        return modelMapper.map(sIgnUpResponseDTO, User.class);
    }
}
