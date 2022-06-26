package com.company.repos;

import com.company.domain.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepos extends JpaRepository<UserAnswer, Long> {
}
