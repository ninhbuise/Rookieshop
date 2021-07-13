package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.User;

public interface UserService {
    public List<User> getUsers();
    public User getUserById(int id);
    public User getUserByUsername(String username);
    public User saveUser(User newUser);
    public User updateUser(User newUser);
    public User updateUserStatus(int id, String status);
    public User updateUserRole(int id, List<String> roles);
    public void deleteUser(int id);
    public boolean existsByUsername(String username);
}
