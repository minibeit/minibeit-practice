package com.miniprac.security.oauth;

import com.miniprac.security.token.Token;
import com.miniprac.security.token.TokenProvider;
import com.miniprac.user.domain.User;
import com.miniprac.user.domain.UserRepository;
import com.miniprac.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Transactional
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    @Value("${oauth2.success.redirect.url}")
    private String url;

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

        response.sendRedirect(url + token.getToken());
    }
}
