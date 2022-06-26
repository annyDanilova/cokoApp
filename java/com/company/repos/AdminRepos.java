package com.company.repos;

import com.company.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepos extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
