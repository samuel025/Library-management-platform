package com.samwellstore.librarymanagement.Repositories;

import com.samwellstore.librarymanagement.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBookIdAndUserId(Long loanId, Long id);

    boolean existsByBookIdAndUserIdAndReturnedFalse(Long bookId, Long userId);
}
