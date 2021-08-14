package com.miniprac.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniprac.security.token.RefreshTokenService;
import com.miniprac.security.token.Token;
import com.miniprac.security.token.TokenProvider;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.repository.UserRepository;
import com.miniprac.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String oAuthId = null;
        if (oauth2User.getAttributes().get("sub") != null) {
            oAuthId = String.valueOf(oauth2User.getAttributes().get("sub"));
        } else {
            oAuthId = String.valueOf(oauth2User.getAttributes().get("id"));
        }
        User user = userRepository.findByOauthId(oAuthId).orElseThrow(UserNotFoundException::new);

        Token token = tokenProvider.generateAccessToken(user);
        Token refreshToken = refreshTokenService.createOrUpdateRefreshToken(user);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new HashMap<>();
        body.put("id", user.getId());
        body.put("name", user.getName());
        body.put("accessToken", token.getToken());
        body.put("accessTokenExpiredAt", token.getExpiredAt().toString());


        ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken.getToken())
                .httpOnly(true)
                .path("/")
                .maxAge(14 * 24 * 60 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
