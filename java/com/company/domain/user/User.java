package com.company.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;

    private String surname;

    private String secondName;

    private Integer schoolCode;

    private String schoolShortName;

    private String password;

    private String municipality;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
