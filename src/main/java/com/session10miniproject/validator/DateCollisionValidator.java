package com.session10miniproject.validator;

import com.session10miniproject.dto.BorrowRequestDTO;
import com.session10miniproject.model.BorrowRequest;
import com.session10miniproject.repository.BorrowRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DateCollisionValidator implements ConstraintValidator<CheckDateCollision, BorrowRequestDTO> {
    @Autowired
    private BorrowRepository borrowRepository;
    @Override
    public boolean isValid(BorrowRequestDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }
        if (dto.getEquipmentId() == null || dto.getBorrowDate() == null || dto.getReturnDate() == null) {
            return true;
        }
        List<BorrowRequest> existed = borrowRepository.findByEquipment(dto.getEquipmentId());
        for (BorrowRequest r : existed) {
            boolean collision = !dto.getBorrowDate().isAfter(r.getReturnDate()) && !dto.getReturnDate().isBefore(r.getBorrowDate());
            if (collision) {
                return false;
            }
        }
        return true;
    }
}