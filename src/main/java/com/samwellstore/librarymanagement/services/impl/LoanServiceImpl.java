package com.samwellstore.librarymanagement.services.impl;

import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanDTO;
import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanPaidResponseDTO;
import com.samwellstore.librarymanagement.Repositories.BookRepository;
import com.samwellstore.librarymanagement.Repositories.LoanRepository;
import com.samwellstore.librarymanagement.Repositories.UserRepository;
import com.samwellstore.librarymanagement.entities.Book;
import com.samwellstore.librarymanagement.entities.Loan;
import com.samwellstore.librarymanagement.entities.User;
import com.samwellstore.librarymanagement.exceptions.AlreadyLoanedException;
import com.samwellstore.librarymanagement.exceptions.LoanRepaidException;
import com.samwellstore.librarymanagement.exceptions.ResourceNotFoundException;
import com.samwellstore.librarymanagement.exceptions.UnauthorizedLoan;
import com.samwellstore.librarymanagement.security.UserPrincipal;
import com.samwellstore.librarymanagement.services.LoanService;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class LoanServiceImpl implements LoanService {

    final LoanRepository loanRepository;
    final BookRepository bookRepository;
    final UserRepository userRepository;
    final Mapper<LoanDTO, Loan> loanMapper;
    final Mapper<Loan, LoanPaidResponseDTO> loanResponseMapper;

    public LoanServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, Mapper<LoanDTO, Loan> loanMapper, Mapper<Loan, LoanPaidResponseDTO> loanResponseMapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanMapper = loanMapper;
        this.loanResponseMapper = loanResponseMapper;
    }


    @Override
    public LoanDTO loanBook(Long bookId, UserPrincipal userPrincipal) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if(loanRepository.existsByBookIdAndUserIdAndReturnedFalse(bookId, userPrincipal.getId())){
            throw new AlreadyLoanedException("You have already loaned this book");
        }
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        loan.setUser(user);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        Loan savedLoan = loanRepository.save(loan);
        bookRepository.save(book);
        return loanMapper.mapFrom(savedLoan);
    }

    @Override
    public LoanPaidResponseDTO repayLoan(Long loanId, UserPrincipal userPrincipal) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        if(!(userPrincipal.getId().equals(loan.getUser().getId()))) {
            throw new UnauthorizedLoan("You are not authorized to repay this loan");
        }
        if(loan.isReturned()) {
            throw new LoanRepaidException("This loan has already been repaid");
        }
        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        Loan savedLoan = loanRepository.save(loan);
        return loanResponseMapper.mapTo(savedLoan);
    }
}