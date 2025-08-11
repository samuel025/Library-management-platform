package com.samwellstore.librarymanagement.services.impl;

import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginRequestDTO;
import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.Repositories.UserRepository;
import com.samwellstore.librarymanagement.entities.User;
import com.samwellstore.librarymanagement.enums.Role;
import com.samwellstore.librarymanagement.security.JWTService;
import com.samwellstore.librarymanagement.services.AuthService;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper<User, SignUpRequestDTO> signUpRequestDTOMapper;

    @Autowired
    Mapper<User, SignUpResponseDTO> signUpResponseDTOMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public SignUpResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())){
            throw new RuntimeException("User already exists");
        };
        User userEntity = signUpRequestDTOMapper.mapFrom(signUpRequestDTO);
        userEntity.setRole(Role.USER);
        userEntity.setPassword(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()));
        User savedUser = userRepository.save(userEntity);
        return signUpResponseDTOMapper.mapTo(savedUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            User user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequestDTO.getEmail()));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmail(),
                            loginRequestDTO.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authentication);
                return new LoginResponseDTO(user.getFullName(), user.getRole().name(), token);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
