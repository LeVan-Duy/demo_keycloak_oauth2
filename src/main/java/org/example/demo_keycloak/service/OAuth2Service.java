package org.example.demo_keycloak.service;

import org.example.demo_keycloak.dto.request.LoginRequest;
import org.example.demo_keycloak.dto.request.LogoutRequest;
import org.example.demo_keycloak.dto.request.ProfileTokenRequest;
import org.example.demo_keycloak.dto.request.RefreshTokenRequest;
import org.example.demo_keycloak.dto.response.LoginResponse;
import org.example.demo_keycloak.dto.response.ProfileResponse;
import org.example.demo_keycloak.dto.response.RefreshTokenResponse;
import org.example.demo_keycloak.dto.response.Response;
import org.springframework.http.ResponseEntity;

public interface OAuth2Service {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);

    ResponseEntity<Response> logout(LogoutRequest logoutRequest);

    ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest request);

    ResponseEntity<ProfileResponse> profile(ProfileTokenRequest request);
}
