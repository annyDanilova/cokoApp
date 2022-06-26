package com.company.controller;

import com.company.domain.test.TestForUser;
import com.company.service.LoginUserService;
import com.company.service.TestForUserService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginUserController {

    @Autowired
    LoginUserService loginUserService;

    @Autowired
    TestForUserService testForUserService;

    @Autowired
    UserService userService;

    @GetMapping("/userLogin")
    public String userLogin() {

        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String userLoginIn(String password) {
        if (loginUserService.correctPassword(password)) {
            return "redirect:/selectTest?p=".concat(password);
        } else {
            return "userLogin";
        }
    }

    @GetMapping("/selectTest")
    public String selectTest(@RequestParam("p") String pass, Model model) {
        List<TestForUser> testsForUser = loginUserService.getTestForThisUser(pass);
        model.addAttribute("testsForUser", testsForUser);
        model.addAttribute("userPassword", pass);
        return "selectTest";
    }

    @GetMapping("/startTest")
    public String startTest(@RequestParam("p") String pass,
                            @RequestParam("testId") String testId,
                            Model model) {
        TestForUser selectTestForUser = testForUserService.getTestForUserById(Long.valueOf(testId));

        String rKey = loginUserService.saveRequest(pass, testId);

        model.addAttribute("testForUser", selectTestForUser);
        model.addAttribute("userPassword", pass);
        model.addAttribute("rKey", rKey);
        return "testingPage";
    }

    @PostMapping("/completeTest")
    public String completeTest() {
        return "";
    }
}
