package com.miniprac.user.web;

import com.miniprac.security.token.RefreshTokenService;
import com.miniprac.security.userdetails.CurrentUser;
import com.miniprac.security.userdetails.CustomUserDetails;
import com.miniprac.user.domain.User;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse.OnlyId> signup(@RequestBody UserRequest.Signup request) {
        User user = userService.create(request);

        return ResponseEntity.created(URI.create("/api/user/" + user.getId())).body(UserResponse.OnlyId.builder().id(user.getId()).build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request) {
        UserResponse.Login response = userService.login(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refreshtoken")
    public UserResponse.Login refreshToken(@CurrentUser CustomUserDetails customUserDetails){
        return refreshTokenService.createAccessToken(customUserDetails.getUser());
    }
}
