package com.samwellstore.librarymanagement.services;

import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;

public interface AdminService {
    SignUpResponseDTO addAdmin(SignUpRequestDTO signUpRequestDTO);
}
