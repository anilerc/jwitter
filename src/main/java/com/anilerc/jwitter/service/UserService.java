package com.anilerc.jwitter.service;

import com.anilerc.jwitter.exception.NotFoundException;
import com.anilerc.jwitter.model.User;
import com.anilerc.jwitter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    protected void addUser(User user) {
        userRepository.save(user);
    }

    protected User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found!"));
    }

}
