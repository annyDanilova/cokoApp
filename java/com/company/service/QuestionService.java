package com.company.service;

import com.company.domain.test.Question;
import com.company.domain.test.QuestionType;
import com.company.repos.QuestionRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepos questionRepos;

    public void create(Question question) {
        if (question.getQuestionType().equals(QuestionType.CHOICE_OF_ANSWER)) {
            String[] answerOptions = question.getAnswerOptions().split(",");
            question.setAnswer1(answerOptions[0]);
            question.setAnswer2(answerOptions[1]);
            question.setAnswer3(answerOptions[2]);
            question.setAnswer4(answerOptions[3]);
        }
        questionRepos.save(question);
    }

    public void modify(Question question) {

    }

    public void delete(Long id) {
        questionRepos.delete(questionRepos.findById(id).get());
    }

    /**
     * метод для получения списка вопросов с номерами (для таблицы)
     *
     * @return
     */
    public List<Question> getQuestionWithNumber() {
        List<Question> questions = questionRepos.findAll();
        questions.sort(Comparator.comparing(Question::getId));
        for (int i = 1; i <= questions.size(); i++) {
            questions.get(i - 1).setNumber(i);
        }
        return questions;
    }
}
