package com.company.controller;

import com.company.domain.Admin;
import com.company.domain.Request;
import com.company.domain.Role;
import com.company.domain.user.User;
import com.company.repos.AdminRepos;
import com.company.repos.RequestRepos;
import com.company.repos.TestForUserRepos;
import com.company.service.DeserializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class AdminRegistrationController {
    @Autowired
    private AdminRepos adminRepos;

    @Autowired
    private RequestRepos requestRepos;



    @GetMapping("/registration")
    public String registration() {

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Admin admin, Model model) {
        Admin adminFromDB = adminRepos.findByUsername(admin.getUsername());

        if (adminFromDB != null) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
        admin.setActive(true);
        admin.setRoles(Collections.singleton(Role.USER));
        adminRepos.save(admin);
        return "redirect:/login";
    }

    @GetMapping("/test")
    public String viewTest(@RequestParam(name = "rKey") String requestKey,
                           Model model) {

        Request request = requestRepos.findByPrivateKey(requestKey);

        model.addAttribute("requestTest", request);
        return "testingPage";
    }

}
