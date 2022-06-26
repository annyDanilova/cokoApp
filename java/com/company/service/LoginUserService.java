package com.company.service;

import com.company.domain.Request;
import com.company.domain.test.TestForUser;
import com.company.domain.user.User;
import com.company.repos.RequestRepos;
import com.company.repos.TestForUserRepos;
import com.company.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Service
public class LoginUserService {

    @Autowired
    UserRepos userRepos;

    @Autowired
    TestForUserRepos testForUserRepos;

    @Autowired
    RequestRepos requestRepos;

    @Autowired
    UserService userService;

    @Autowired
    TestForUserService testForUserService;

    public boolean correctPassword(String password){
        return userRepos.findAll().stream()
                .anyMatch(el->el.getPassword().equals(password));
    }

    public List<TestForUser> getTestForThisUser(String password){
        User user = userRepos.findUserByPassword(password);
        return testForUserRepos.findTestForUserByUserRole(user.getUserRole());
    }

    public String saveRequest(String pass,
                            String testId){
        TestForUser selectTestForUser = testForUserService.getTestForUserById(Long.valueOf(testId));
        User user = userService.getUserByPassword(pass);
        Request request = new Request(selectTestForUser, user);
        request.setPrivateKey(UUID.randomUUID().toString());
        requestRepos.save(request);
        return request.getPrivateKey();
    }

}
