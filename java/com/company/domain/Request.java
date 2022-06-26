package com.company.domain;

import com.company.domain.test.TestForUser;
import com.company.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * заявка, объединяющая тест и тестируемого
 */

@Entity
@Getter
@Setter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String privateKey;

    @ManyToOne(cascade = {CascadeType.ALL})
    private TestForUser testForUser;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToMany
    private List<UserAnswer> userAnswers = new ArrayList<>();

    private Integer countOfTrueAnswers;

    private Integer countOfAllQuestions;

    private Integer percent;

    public Request(TestForUser testForUser, User user) {
        this.testForUser = testForUser;
        this.user = user;
    }

    public Request() {

    }
}
