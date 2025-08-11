package com.samwellstore.librarymanagement.DTOs.SignUpDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDTO {
    private String fullName;
    private String email;
}
