package com.company.controller;

import com.company.domain.user.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public String create(User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/modifyUser")
    public String modify(User user) {
        userService.modify(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser")
    public String delete(User user) {
        userService.delete(user);
        return "redirect:/admin";
    }
}
