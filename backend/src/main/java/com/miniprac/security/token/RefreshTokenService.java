package com.miniprac.security.token;

import com.miniprac.user.domain.RefreshToken;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public void createOrUpdateRefreshToken(User user){
        Token createdRefreshToken = tokenProvider.generateRefreshToken();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(user.getId());

        if (optionalRefreshToken.isPresent()) {
            optionalRefreshToken.get().update(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt());
        } else {
            RefreshToken refreshToken = RefreshToken.create(createdRefreshToken.getToken(), createdRefreshToken.getExpiredAt(), user);
            refreshTokenRepository.save(refreshToken);
        }
    }
}
