package org.example.demo_keycloak.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.demo_keycloak.dto.request.LoginRequest;
import org.example.demo_keycloak.dto.request.LogoutRequest;
import org.example.demo_keycloak.dto.request.ProfileTokenRequest;
import org.example.demo_keycloak.dto.request.RefreshTokenRequest;
import org.example.demo_keycloak.dto.response.LoginResponse;
import org.example.demo_keycloak.dto.response.ProfileResponse;
import org.example.demo_keycloak.dto.response.RefreshTokenResponse;
import org.example.demo_keycloak.dto.response.Response;
import org.example.demo_keycloak.service.OAuth2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class OAuth2Controller {

    OAuth2Service oAuth2Service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return oAuth2Service.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestBody LogoutRequest request) {
        return oAuth2Service.logout(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return oAuth2Service.refreshToken(request);
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> refresh(@RequestBody ProfileTokenRequest request) {
        return oAuth2Service.profile(request);
    }

}
