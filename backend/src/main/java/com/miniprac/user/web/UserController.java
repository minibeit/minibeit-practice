package com.miniprac.user.web;

import com.miniprac.security.token.RefreshTokenService;
import com.miniprac.security.userdetails.CurrentUser;
import com.miniprac.security.userdetails.CustomUserDetails;
import com.miniprac.user.domain.User;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private static final String REFRESH_TOKEN = "refresh_token";

    @PostMapping("/signup")
    public ResponseEntity<UserResponse.OnlyId> signup(@RequestBody UserRequest.Signup request) {
        User user = userService.create(request);

        return ResponseEntity.created(URI.create("/api/user/" + user.getId())).body(UserResponse.OnlyId.builder().id(user.getId()).build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request, HttpServletResponse response) {
        UserResponse.Login loginResponse = userService.login(request);
        createCookie(response, loginResponse.getRefreshToken());

        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<UserResponse.Login> refreshToken(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {
        UserResponse.Login loginResponse = refreshTokenService.createAccessToken(refreshToken);
        createCookie(response, loginResponse.getRefreshToken());

        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CurrentUser CustomUserDetails customUserDetails, HttpServletResponse response) {
        userService.logout(customUserDetails.getUser());
        deleteCookie(response);

        return ResponseEntity.ok().build();
    }

    private void createCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(14 * 24 * 60 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    private void deleteCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }
}
