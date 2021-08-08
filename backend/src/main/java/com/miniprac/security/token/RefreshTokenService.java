package com.miniprac.security.token;

import com.miniprac.user.domain.RefreshToken;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.repository.RefreshTokenRepository;
import com.miniprac.user.domain.repository.UserRepository;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.exception.RefreshTokenExpiredException;
import com.miniprac.user.service.exception.RefreshTokenNotFoundException;
import com.miniprac.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public void createOrUpdateRefreshToken(User user) {
        Token createdRefreshToken = tokenProvider.generateRefreshToken();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(user.getId());

        if (optionalRefreshToken.isPresent()) {
            optionalRefreshToken.get().update(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt());
        } else {
            RefreshToken refreshToken = RefreshToken.create(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt(), user);
            refreshTokenRepository.save(refreshToken);
        }
    }

    public UserResponse.Login createAccessToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId).orElseThrow(RefreshTokenNotFoundException::new);
        if (!refreshToken.verifyExpiration()) {
            throw new RefreshTokenExpiredException();
        }
        Token token = tokenProvider.generateRefreshToken();
        refreshToken.update(token.getToken(), token.getExpiredAt());

        return UserResponse.Login.build(user.getId(), user.getName(), tokenProvider.generateAccessToken(user));
    }
}
