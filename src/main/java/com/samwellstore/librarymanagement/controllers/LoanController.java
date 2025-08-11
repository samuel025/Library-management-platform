package com.samwellstore.librarymanagement.controllers;


import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanDTO;
import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanPaidResponseDTO;
import com.samwellstore.librarymanagement.security.UserPrincipal;
import com.samwellstore.librarymanagement.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    final LoanService loanService;
    public LoanController(LoanService loanService) {
            this.loanService = loanService;
    }

    @PostMapping(path = "/loan/book/{bookId}")
    public ResponseEntity<LoanDTO> takeLoan(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("bookId") Long bookId) {
        LoanDTO loanDTO = loanService.loanBook(bookId, userPrincipal);
        return ResponseEntity.ok(loanDTO);
    }

    @PutMapping(path = "/repay/loan/{loanId}")
    public ResponseEntity<LoanPaidResponseDTO> repayLoan(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("loanId") Long loanId) {
        LoanPaidResponseDTO loanPaidResponseDTO = loanService.repayLoan(loanId, userPrincipal);
        return ResponseEntity.ok(loanPaidResponseDTO);
    }
}
