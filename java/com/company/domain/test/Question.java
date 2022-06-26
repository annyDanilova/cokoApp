package com.company.domain.test;

import com.company.domain.user.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //тип тестирования по ролям пользователя
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(length = 1024)
    private String quest;
    private String answer;
    private String description;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String answerOptions;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && userRole == question.userRole && Objects.equals(quest, question.quest) && Objects.equals(answer, question.answer) && Objects.equals(description, question.description) && Objects.equals(number, question.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole, quest, answer, description, number);
    }
}
