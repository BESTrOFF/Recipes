package com.bystrov.recipes5.controller;

import com.bystrov.recipes5.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Integer userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUserById(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String graterThenUser(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("allUsers", userService.userGraterThenList(userId));
        return "admin";
    }
}
