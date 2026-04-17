package com.session10miniproject.controller;

import com.session10miniproject.service.BorrowService;
import com.session10miniproject.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/equipment-list")
    public String listEquipment(Model model) {
        model.addAttribute("equipmentList", equipmentService.getAllEquipment());
        return "admin/equipment-list";
    }

    @GetMapping("/request-list")
    public String listRequests(Model model) {
        model.addAttribute("requestList", borrowService.getAllRequests());
        return "admin/request-list";
    }

    @PostMapping("/approve/{id}")
    public String approveRequest(@PathVariable("id") String id) {
        borrowService.approveRequest(id);
        return "redirect:/admin/request-list";
    }

    @PostMapping("/reject/{id}")
    public String rejectRequest(@PathVariable("id") String id) {
        borrowService.rejectRequest(id);
        return "redirect:/admin/request-list";
    }
}