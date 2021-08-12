package com.miniprac.security.token;

import com.miniprac.security.exception.InvalidRefreshTokenException;
import com.miniprac.user.domain.RefreshToken;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.repository.RefreshTokenRepository;
import com.miniprac.user.domain.repository.UserRepository;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.security.exception.RefreshTokenNotFoundException;
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

    public Token createOrUpdateRefreshToken(User user) {
        Token createdRefreshToken = tokenProvider.generateRefreshToken(user);
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(user.getId());

        if (optionalRefreshToken.isPresent()) {
            optionalRefreshToken.get().update(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt());
        } else {
            RefreshToken refreshToken = RefreshToken.create(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt(), user);
            refreshTokenRepository.save(refreshToken);
        }
        return createdRefreshToken;
    }

    public UserResponse.Login createAccessToken(String refreshToken) {
        //refresh token 유효성 검사와 userId 가져오기
        Long userId = tokenProvider.getUserIdFromRefreshToken(refreshToken);

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        //db의 refresh 토큰과 일치하는 지 검사
        RefreshToken findRefreshToken = refreshTokenRepository.findByUserId(userId).orElseThrow(RefreshTokenNotFoundException::new);
        if(!findRefreshToken.isSame(refreshToken)){
            throw new InvalidRefreshTokenException();
        }
        //refresh token 업데이트
        Token createdRefreshToken = tokenProvider.generateRefreshToken(user);
        findRefreshToken.update(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt());

        return UserResponse.Login.build(user.getId(), user.getName(), tokenProvider.generateAccessToken(user), createdRefreshToken);
    }


    public void deleteRefreshTokenByUser(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
    }
}
