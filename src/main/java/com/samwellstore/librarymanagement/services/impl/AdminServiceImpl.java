package com.samwellstore.librarymanagement.services.impl;

import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.Repositories.UserRepository;
import com.samwellstore.librarymanagement.entities.User;
import com.samwellstore.librarymanagement.enums.Role;
import com.samwellstore.librarymanagement.services.AdminService;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper<User, SignUpRequestDTO> signUpRequestDTOMapper;

    @Autowired
    Mapper<User, SignUpResponseDTO> signUpResponseDTOMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public SignUpResponseDTO addAdmin(SignUpRequestDTO signUpRequestDTO) {
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
            throw new RuntimeException("User already exists");
        }
        User user = signUpRequestDTOMapper.mapFrom( signUpRequestDTO);
        user.setRole(Role.ADMIN);
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return signUpResponseDTOMapper.mapTo(savedUser);
    }

}
