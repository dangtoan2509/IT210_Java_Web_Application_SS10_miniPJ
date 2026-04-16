package com.session10miniproject.validator;

import com.session10miniproject.dto.BorrowRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDateRangeValidator implements ConstraintValidator<ValidDateRange, BorrowRequestDTO> {
    @Override
    public boolean isValid(BorrowRequestDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }
        if (dto.getBorrowDate() == null || dto.getReturnDate() == null) {
            return true;
        }
        return !dto.getReturnDate()
                .isBefore(dto.getBorrowDate());
    }
}