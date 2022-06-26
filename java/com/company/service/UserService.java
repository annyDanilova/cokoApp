package com.company.service;

import com.company.domain.user.User;
import com.company.domain.user.UserRole;
import com.company.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;

    public void create(User user) {
        user.setPassword(generateUnicPassword());
        userRepos.save(user);
    }

    public void modify(User user) {

    }


    public void delete(User user) {

    }

    /**
     * Находит пользователя по роли
     *
     * @param userRole
     * @return
     */
    public List<User> findUserOnRole(UserRole userRole) {
        List<User> userList = userRepos.findAll();
        return userList.stream().filter(user -> user.getUserRole().equals(userRole)).collect(Collectors.toList());
    }

    /**
     * Возаращает полное имя
     */
    public String getFullName(User user){
        return user.getSurname().concat(" ").concat(user.getFirstname()).concat(" ").concat(user.getSecondName());
    }

    private String generateUnicPassword(){
        String pass = UUID.randomUUID().toString().substring(0,8);
        boolean isPresentPass = false;

//        while (!isPresentPass){
//            pass= UUID.randomUUID().toString().substring(0,8);
//            String finalPass = pass;
//            isPresentPass = userRepos.findAll().stream()
//                    .anyMatch(el -> !el.getPassword().equals(finalPass));
//        }
        return pass;
    }

    public User getUserByPassword(String pass){
        return userRepos.findUserByPassword(pass);
    }

}
