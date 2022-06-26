package com.company.controller;


import com.company.domain.test.Question;
import com.company.domain.test.QuestionType;
import com.company.domain.test.TestForUser;
import com.company.domain.user.UserRole;
import com.company.repos.QuestionRepos;
import com.company.repos.RequestRepos;
import com.company.repos.TestForUserRepos;
import com.company.service.QuestionService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private TestForUserRepos testForUserRepos;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private RequestRepos requestRepos;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    //тут подтягиваем в модель все данные из бд
    @GetMapping("/admin")
    public String main(Model model) {
        Map<UserRole, String> userRoleMap = getUserRoleMap(UserRole.values());
        Map<QuestionType, String> questionTypeMap = getQuestionType(QuestionType.values());

        List<TestForUser> testForUserList = testForUserRepos.findAll();
        List<Question> questions = questionService.getQuestionWithNumber();

        model.addAttribute("userRole", userRoleMap)
                .addAttribute("questionType", questionTypeMap)

                .addAttribute("usersSupervisorPpe", userService.findUserOnRole(UserRole.SUPERVISOR_PPE))
                .addAttribute("usersTechMan", userService.findUserOnRole(UserRole.TECH_MAN))
                .addAttribute("organizerPpe", userService.findUserOnRole(UserRole.ORGANIZER_PPE))
                .addAttribute("publicObserver", userService.findUserOnRole(UserRole.PUBLIC_OBSERVER))
                .addAttribute("expert", userService.findUserOnRole(UserRole.EXPERT))
                .addAttribute("student", userService.findUserOnRole(UserRole.STUDENT))

                .addAttribute("testForUserList", testForUserList)
                .addAttribute("questions", questions)
                .addAttribute("requests", requestRepos.findAll());


        return "admin";
    }

    private Map<UserRole, String> getUserRoleMap(UserRole[] userRoles) {
        Map<UserRole, String> dataMap = new HashMap<>();
        Arrays.stream(userRoles)
                .forEach(el -> dataMap.put(el, el.getRole()));
        return dataMap;
    }

    private Map<QuestionType, String> getQuestionType(QuestionType[] questionTypes){
        Map<QuestionType, String> dataMap = new LinkedHashMap<>();
        Arrays.stream(questionTypes)
                .forEach(el -> dataMap.put(el, el.getType()));
        return dataMap;
    }

}
