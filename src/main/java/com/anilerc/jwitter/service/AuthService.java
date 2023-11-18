package com.anilerc.jwitter.service;

import com.anilerc.jwitter.dto.request.LoginRequest;
import com.anilerc.jwitter.dto.request.RegisterRequest;
import com.anilerc.jwitter.dto.response.UserDto;
import com.anilerc.jwitter.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy;

    public UserDto register(RegisterRequest request) {

        var username = request.username();
        var password = passwordEncoder.encode(request.password());
        var newUser = User.builder().username(username).password(password).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).avatarUrl("https://www.milton.edu/wp-content/uploads/2019/11/avatar-placeholder.jpg").build();

        userService.addUser(newUser);

        return new UserDto(newUser.getUsername());
    }

    public void login(LoginRequest loginReqBody, HttpServletRequest request, HttpServletResponse response) {
        var username = loginReqBody.username();
        var password = loginReqBody.password();

        var token = UsernamePasswordAuthenticationToken.unauthenticated(username, password);

        var auth = authenticationManager.authenticate(token);

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();

        context.setAuthentication(auth);

        securityContextHolderStrategy.setContext(context);

        securityContextRepository.saveContext(context, request, response);
    }

}
