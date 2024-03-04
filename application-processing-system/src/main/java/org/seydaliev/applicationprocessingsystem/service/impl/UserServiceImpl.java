package org.seydaliev.applicationprocessingsystem.service.impl;

import org.seydaliev.applicationprocessingsystem.model.User;
import org.seydaliev.applicationprocessingsystem.repository.RoleRepository;
import org.seydaliev.applicationprocessingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements org.seydaliev.applicationprocessingsystem.service.UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRepository userRepository1, RoleRepository roleRepository1) {
        this.userRepository = userRepository1;
        this.roleRepository = roleRepository1;
    }

    public User createUser(User user) {
        if (user == null) {

        }
       return userRepository.save(user);
    }

}
