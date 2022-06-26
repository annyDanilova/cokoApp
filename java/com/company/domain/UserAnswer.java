package com.company.domain;

import com.company.domain.test.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;
    private boolean isTrueAnswer;

    @ManyToOne
    private Question question;

    public UserAnswer(String answer, boolean isTrueAnswer, Question question) {
        this.answer = answer;
        this.isTrueAnswer = isTrueAnswer;
        this.question = question;
    }

    public UserAnswer() {

    }
}
