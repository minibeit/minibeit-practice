package com.miniprac.user.service;

import com.miniprac.security.token.TokenProvider;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.UserRepository;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.exception.EmailExistedException;
import com.miniprac.user.service.exception.PasswordWrongException;
import com.miniprac.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public User create(UserRequest.Signup request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailExistedException();
        }
        return userRepository.save(User.create(request.getName(), request.getEmail(), request.getPassword(), passwordEncoder));
    }

    public UserResponse.Login login(UserRequest.Login request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordWrongException();
        }

        return UserResponse.Login.build(user.getId(), user.getName(), tokenProvider.generateAccessToken(user));
    }
}
