package com.company.repos;

import com.company.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepos extends JpaRepository<Request, Long> {
    Request findByPrivateKey(String privateKey);
}
