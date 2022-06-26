package com.company.repos;

import com.company.domain.test.TestForUser;
import com.company.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestForUserRepos extends JpaRepository<TestForUser, Long> {
    List<TestForUser> findTestForUserByUserRole(UserRole userRole);
}
