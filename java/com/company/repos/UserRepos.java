package com.company.repos;

import com.company.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {
    User findUserByPassword(String password);
}
