package com.samwellstore.librarymanagement.services;

import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanDTO;
import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanPaidResponseDTO;
import com.samwellstore.librarymanagement.security.UserPrincipal;

public interface LoanService {
    LoanDTO loanBook(Long bookId, UserPrincipal userPrincipal);
    LoanPaidResponseDTO repayLoan(Long loanId, UserPrincipal userPrincipal);

}
