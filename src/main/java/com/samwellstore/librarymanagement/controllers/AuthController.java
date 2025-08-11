package com.samwellstore.librarymanagement.controllers;


import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginRequestDTO;
import com.samwellstore.librarymanagement.DTOs.LoginDTOs.LoginResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpResponseDTO;
import com.samwellstore.librarymanagement.DTOs.SignUpDTOs.SignUpRequestDTO;
import com.samwellstore.librarymanagement.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<SignUpResponseDTO> register(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponseDTO = authService.signup(signUpRequestDTO);
        return ResponseEntity.ok(signUpResponseDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping(path = "/test/{password}")
    public ResponseEntity<String> gethash(@PathVariable("password") String password) {
        String hashPassword = bCryptPasswordEncoder.encode(password);
        return ResponseEntity.ok(hashPassword);
    }


}
