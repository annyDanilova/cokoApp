package com.company.controller;

import com.company.domain.Request;
import com.company.domain.user.User;
import com.company.domain.user.UserRole;
import com.company.service.DeserializeService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentRegistrationController {
    @Autowired
    private DeserializeService deserializeService;

    @Autowired
    UserService userService;


    @GetMapping("/studentRegistration")
    public String registrationOfStudentGet(Model model) {
        List municipalities = deserializeService.getObjectOfResources("/xml/municipality.xml", List.class);
        model.addAttribute("municipalities", municipalities);
        return "studentRegistration";
    }

    @PostMapping("/studentRegistration")
    public String registrationOfStudentPost(User user) {
        user.setUserRole(UserRole.STUDENT);
        userService.create(user);
        return "redirect:/selectTest?p=".concat(user.getPassword());
    }
}
