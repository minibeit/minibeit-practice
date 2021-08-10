package com.miniprac.user.web;

import com.miniprac.security.token.RefreshTokenService;
import com.miniprac.user.domain.User;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

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
    public UserResponse.Login refreshToken(@RequestHeader Map<String, Object> requestHeader) {
        String refresh_token = requestHeader.get("refresh_token").toString();

        return refreshTokenService.createAccessToken(refresh_token);
    }
}
