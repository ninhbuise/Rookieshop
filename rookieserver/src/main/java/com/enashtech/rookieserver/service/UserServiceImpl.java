package com.enashtech.rookieserver.service;

import java.util.List;

import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.handleException.UserNotFoundException;
import com.enashtech.rookieserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User addNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User newUser, int id) {
        return userRepository.findById(id)
            .map(user -> {
                user.setPassword(newUser.getPassword());
                user.setUrl_avatar(newUser.getUrl_avatar());
                user.setRoles(newUser.getRoles());
                return userRepository.save(user);
            })
            .orElseGet(() -> {
                newUser.setId(id);
                return userRepository.save(newUser);
            }
        );
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
