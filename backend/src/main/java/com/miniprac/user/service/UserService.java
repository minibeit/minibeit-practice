package com.miniprac.user.service;

import com.miniprac.user.domain.User;
import com.miniprac.user.domain.UserRepository;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.service.exception.EmailExistedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserRequest.Signup request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailExistedException();
        }
        return userRepository.save(User.create(request.getName(), request.getEmail(), request.getPassword(), passwordEncoder));
    }
}
