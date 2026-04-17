package com.session10miniproject.service;

import com.session10miniproject.dto.BorrowRequestDTO;
import com.session10miniproject.model.BorrowRequest;
import com.session10miniproject.model.Equipment;
import com.session10miniproject.repository.BorrowRepository;
import com.session10miniproject.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public void createBorrowRequest(BorrowRequestDTO dto) {
        BorrowRequest request = new BorrowRequest();
        request.setEquipmentId(dto.getEquipmentId());
        request.setStudentName(dto.getStudentName());
        request.setStudentId(dto.getStudentId());
        request.setEmail(dto.getEmail());
        request.setQuantity(dto.getQuantity());
        request.setBorrowDate(dto.getBorrowDate());
        request.setReturnDate(dto.getReturnDate());
        request.setReason(dto.getReason());

        borrowRepository.save(request);

        // Deduct quantity if available (Basic logic)
        // Normally, this happens when an admin APPROVES it, but for simple flow we might not have full complex state logic.
    }

    public List<BorrowRequest> getAllRequests() {
        return borrowRepository.findAll();
    }

    public void approveRequest(String requestId) {
        BorrowRequest request = borrowRepository.findAll().stream()
                .filter(r -> r.getId().equals(requestId))
                .findFirst().orElse(null);
        if (request != null && "PENDING".equals(request.getStatus())) {
            // Find equipment and decrease balance here if we strictly follow admin approval flow
            Equipment equipment = equipmentRepository.findById(request.getEquipmentId()).orElse(null);
            if (equipment != null && equipment.getAvailableQuantity() >= request.getQuantity()) {
                equipment.setAvailableQuantity(equipment.getAvailableQuantity() - request.getQuantity());
                request.setStatus("APPROVED");
            }
        }
    }

    public void rejectRequest(String requestId) {
        BorrowRequest request = borrowRepository.findAll().stream()
                .filter(r -> r.getId().equals(requestId))
                .findFirst().orElse(null);
        if (request != null && "PENDING".equals(request.getStatus())) {
            request.setStatus("REJECTED");
        }
    }
}