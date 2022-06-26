package com.company.controller;

import com.company.domain.Request;
import com.company.domain.UserAnswer;
import com.company.domain.test.Question;
import com.company.repos.RequestRepos;
import com.company.repos.UserAnswerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    @Autowired
    private RequestRepos requestRepos;
    @Autowired
    private UserAnswerRepos userAnswerRepos;

    @PostMapping(value = "/reviewTest")
    public String reviewTest(@RequestParam(value = "answerQuestion") String[] answerQuestion,
                             @RequestParam(value = "rKey") String privateRequestKey,
                             Model model) {

        Request request = requestRepos.findByPrivateKey(privateRequestKey);

        int countOfTrueAnswers = 0;
        final int countOfQuestions = request.getTestForUser().getQuestions().size();

        for (int i = 0; i < countOfQuestions; i++) {
            if (request.getTestForUser().getQuestions().get(i).getAnswer().equals(answerQuestion[i])) {
                countOfTrueAnswers++;
                request.getUserAnswers()
                        .add(identifyStudentAnswer(true, request.getTestForUser().getQuestions().get(i),
                                answerQuestion[i]));
            }
            else {
                request.getUserAnswers()
                        .add(identifyStudentAnswer(false, request.getTestForUser().getQuestions().get(i),
                                answerQuestion[i]));
            }
        }
        request.setCountOfTrueAnswers(countOfTrueAnswers);
        request.setCountOfAllQuestions(countOfQuestions);
        request.setPercent((countOfTrueAnswers*100)/countOfQuestions);
        requestRepos.save(request);

        model.addAttribute("countOfTrueAnswers", countOfTrueAnswers)
                .addAttribute("countOfAllQuestions", countOfQuestions)
                .addAttribute("requestTest", request);

        return "resultOfTest";
    }

    private UserAnswer identifyStudentAnswer(boolean isTrueAnswer, Question question, String studentAnswer) {
         UserAnswer userAnswerEl = new UserAnswer(studentAnswer, isTrueAnswer, question);
         userAnswerRepos.save(userAnswerEl);
         return userAnswerEl;
    }
}
