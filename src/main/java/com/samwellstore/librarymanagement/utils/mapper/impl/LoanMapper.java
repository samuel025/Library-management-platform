package com.samwellstore.librarymanagement.utils.mapper.impl;

import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanDTO;
import com.samwellstore.librarymanagement.entities.Loan;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class LoanMapper implements Mapper<LoanDTO, Loan> {

    final ModelMapper modelMapper;

    public LoanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Loan mapTo(LoanDTO loanDTO) {
        return modelMapper.map(loanDTO, Loan.class);
    }

    @Override
    public LoanDTO mapFrom(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }
}
