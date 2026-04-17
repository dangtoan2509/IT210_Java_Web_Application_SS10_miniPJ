package com.session10miniproject.controller;

import com.session10miniproject.dto.BorrowRequestDTO;
import com.session10miniproject.model.Equipment;
import com.session10miniproject.service.BorrowService;
import com.session10miniproject.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("equipmentList", equipmentService.getAllEquipment());
        return "student/catalog";
    }

    @GetMapping("/borrow/{id}")
    public String showBorrowForm(@PathVariable("id") String id, Model model) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            return "redirect:/student/catalog";
        }

        BorrowRequestDTO dto = new BorrowRequestDTO();
        dto.setEquipmentId(id);
        if ("LAB".equals(equipment.getType())) {
            dto.setQuantity(1); // Default to 1 for Labs
        }

        model.addAttribute("borrowRequestDTO", dto);
        model.addAttribute("equipment", equipment);
        return "student/borrow-form";
    }

    @PostMapping("/borrow/{id}")
    public String processBorrowForm(@PathVariable("id") String id,
                                    @Valid @ModelAttribute("borrowRequestDTO") BorrowRequestDTO dto,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            return "redirect:/student/catalog";
        }

        // VAL-03 Logic Check
        if (dto.getQuantity() != null && dto.getQuantity() > equipment.getAvailableQuantity()) {
            bindingResult.rejectValue("quantity", "error.borrowRequestDTO", "Số lượng mượn không được vượt quá số lượng hiện tại.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("equipment", equipment);
            return "student/borrow-form";
        }

        borrowService.createBorrowRequest(dto);

        // Bonus 2: Flash Attributes
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký mượn thành công!");
        return "redirect:/student/catalog";
    }
}
