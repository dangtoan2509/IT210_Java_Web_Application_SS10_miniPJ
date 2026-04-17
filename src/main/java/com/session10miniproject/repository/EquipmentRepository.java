package com.session10miniproject.repository;

import com.session10miniproject.model.Equipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepository {
    private final List<Equipment> equipmentList = new ArrayList<>();

    public EquipmentRepository() {
        // Initialize mock data
        equipmentList.add(new Equipment("EQ01", "Màn hình rời Dell 24 inch", "DEVICE", 10, 10, "https://images.unsplash.com/photo-1541807084-5c52b6b3adef?auto=format&fit=crop&w=400&q=80"));
        equipmentList.add(new Equipment("EQ02", "Cáp kết nối HDMI", "DEVICE", 20, 20, "https://images.unsplash.com/photo-1537498425277-c283d32ef9db?auto=format&fit=crop&w=400&q=80"));
        equipmentList.add(new Equipment("LAB01", "Phòng Lab Mac (Room A1)", "LAB", 1, 1, "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?auto=format&fit=crop&w=400&q=80"));
        equipmentList.add(new Equipment("LAB02", "Phòng Lab Windows (Room B2)", "LAB", 1, 1, "https://images.unsplash.com/photo-1580894732444-8ecded7900cd?auto=format&fit=crop&w=400&q=80"));
    }

    public List<Equipment> findAll() {
        return equipmentList;
    }

    public Optional<Equipment> findById(String id) {
        return equipmentList.stream()
                .filter(eq -> eq.getId().equals(id))
                .findFirst();
    }
}