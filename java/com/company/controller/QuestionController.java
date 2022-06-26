package com.company.controller;

import com.company.domain.test.Question;
import com.company.domain.user.UserRole;
import com.company.repos.QuestionRepos;
import com.company.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepos questionRepos;

    @PostMapping("/addQuestion")
    public String create(Question question) {
        questionService.create(question);
        return "redirect:/admin";
    }

    @GetMapping("/modifyQuestion")
    public String modify(Question question) {
        questionService.modify(question);
        return "redirect:/admin";
    }

    @GetMapping("/deleteQuestion")
    public String delete(@RequestParam("id") Long id) {
        questionService.delete(id);
        return "redirect:/admin";
    }

    /**
     * Метод для получения вопроса по id
     * @param id
     * @return
     */
    @GetMapping(value = "/getQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Question getQuestionById(@RequestParam("id") Long id){
        Question question = questionRepos.findById(id).get();
        return question;
    }

    /**
     * Метод для получения вопросов по роли
     * @param role
     * @return
     */
    @GetMapping(value = "/getQuestionForRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Question> getQuestionForRole(@RequestParam("role") String role){
        List<Question> questionForRole = questionRepos.findQuestionByUserRole(UserRole.valueOf(role));
        questionForRole.sort(Comparator.comparing(Question::getId));
        return questionForRole;
    }
}
