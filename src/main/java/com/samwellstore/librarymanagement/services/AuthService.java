package com.samwellstore.librarymanagement.services;


import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginRequestDTO;
import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;


public interface AuthService {
    SignUpResponseDTO signup(SignUpRequestDTO signUpRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
