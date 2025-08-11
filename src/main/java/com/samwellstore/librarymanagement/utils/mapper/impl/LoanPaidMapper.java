package com.samwellstore.librarymanagement.utils.mapper.impl;

import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanDTO;
import com.samwellstore.librarymanagement.DTOs.LoanDTOs.LoanPaidResponseDTO;
import com.samwellstore.librarymanagement.entities.Loan;
import com.samwellstore.librarymanagement.utils.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class LoanPaidMapper implements Mapper<Loan, LoanPaidResponseDTO> {

    final ModelMapper modelMapper;

    public LoanPaidMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LoanPaidResponseDTO mapTo(Loan loan) {
        return modelMapper.map(loan, LoanPaidResponseDTO.class);
    }

    @Override
    public Loan mapFrom(LoanPaidResponseDTO loanPaidResponseDTO) {
        return modelMapper.map(loanPaidResponseDTO, Loan.class);
    }
}
