package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.User;

public interface UserService {
    public List<User> getUsers();
    public User getUserById(int id);
    public User addNewUser(User newUser);
    public User updateUser(User newUser, int id);
    public void deleteUser(int id);
}
