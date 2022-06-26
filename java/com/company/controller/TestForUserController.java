package com.company.controller;

import com.company.domain.test.Question;
import com.company.domain.test.TestForUser;
import com.company.service.TestForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestForUserController {

    @Autowired
    private TestForUserService testForUserService;

    @PostMapping("/addTestForUser")
    public String create(TestForUser testForUser) {
        testForUserService.create(testForUser);
        return "redirect:/admin";
    }

    @PostMapping("/modifyTestForUser")
    public ResponseEntity modify(@RequestBody List<String> ids) {
        testForUserService.modify(ids);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/deleteTestForUser")
    public String delete(@RequestParam("id") Long id) {
        testForUserService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/getQuestionForTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Question> getQuestion(@RequestParam("id") Long id) {
        return testForUserService.getQuestionForeTest(id);
    }
}
