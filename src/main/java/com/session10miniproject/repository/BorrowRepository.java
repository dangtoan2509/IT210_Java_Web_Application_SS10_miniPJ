package com.session10miniproject.repository;

import com.session10miniproject.model.BorrowRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BorrowRepository {
    private final List<BorrowRequest> requests = new ArrayList<>();

    public void save(BorrowRequest request) {
        requests.add(request);
    }

    public List<BorrowRequest> findAll() {
        return requests;
    }

    public List<BorrowRequest> findByEquipmentId(String equipmentId) {
        return requests.stream()
                .filter(req -> req.getEquipmentId().equals(equipmentId))
                .toList();
    }
}