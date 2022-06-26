package com.company.domain.test;

import com.company.domain.user.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class TestForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    //тип тестирования по ролям пользователя
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Question> questions = new ArrayList<>();
}
