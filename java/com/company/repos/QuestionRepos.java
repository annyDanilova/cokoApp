package com.company.repos;

import com.company.domain.test.Question;
import com.company.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepos extends JpaRepository<Question, Long> {
    public List<Question> findQuestionByUserRole(UserRole userRole);
}
