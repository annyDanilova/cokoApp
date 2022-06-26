package com.company.service;

import com.company.domain.test.Question;
import com.company.domain.test.TestForUser;
import com.company.repos.QuestionRepos;
import com.company.repos.TestForUserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TestForUserService {

    @Autowired
    private TestForUserRepos testForUserRepos;

    @Autowired
    private QuestionRepos questionRepos;

    public void create(TestForUser testForUser){
        testForUserRepos.save(testForUser);
    }

    public void modify(List<String> ids){
        String testId = ids.get(0);
        TestForUser test = testForUserRepos.findById(Long.valueOf(testId)).get();
        test.getQuestions().clear();
        for (int i = 1; i < ids.size(); i++) {
            test.getQuestions().add(questionRepos.findById(Long.valueOf(ids.get(i))).get());
        }
        testForUserRepos.save(test);
    }

    public void delete(Long id){
        testForUserRepos.delete(testForUserRepos.findById(id).get());
    }

    public List<Question> getQuestionForeTest(Long testId){
        List<Question> questions = testForUserRepos.findById(testId).get().getQuestions();
        questions.sort(Comparator.comparing(Question::getId));
        return questions;
    }

    public TestForUser getTestForUserById(Long id){
        return testForUserRepos.findById(id).get();
    }
}
