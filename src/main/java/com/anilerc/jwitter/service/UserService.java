package com.anilerc.jwitter.service;

import com.anilerc.jwitter.model.User;
import com.anilerc.jwitter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

}
