package com.example.service;

import com.example.dao.UserDao;
import com.example.domain.User;

public class UserService {
    private UserDao userDao = new UserDao();
    public User findUser(String username,String password) {
        return  userDao.findUser(username,password);
    }

    public boolean findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public boolean registerUser(String username,String password){
        return userDao.registerUser(username,password);
    }

}
