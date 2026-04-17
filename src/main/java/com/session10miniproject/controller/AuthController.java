package com.session10miniproject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login";
    }

    @PostMapping("/do-login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        // Hard-coded admin credentials for simplicity without DB
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/equipment-list";
        }

        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác.");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/catalog";
    }
}
