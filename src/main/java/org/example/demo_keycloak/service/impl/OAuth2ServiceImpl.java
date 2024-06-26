package org.example.demo_keycloak.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OAuth2ServiceImpl implements OAuth2Service {

    final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    String issueUrl;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    String tokenUrl;

    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    String grantType;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        HttpHeaders httpHeaders = httpHeaders();
        MultiValueMap<String, String> map = mapUtils();
        map.add("grant_type", grantType);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<LoginResponse> loginResponseResponseEntity = restTemplate
                .postForEntity(tokenUrl, httpEntity, LoginResponse.class);

        return new ResponseEntity<>(loginResponseResponseEntity.getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> logout(LogoutRequest logoutRequest) {
        HttpHeaders httpHeaders = httpHeaders();
        MultiValueMap<String, String> map = mapUtils();
        map.add("refresh_token", logoutRequest.getToken());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<Response> responseEntity = restTemplate
                .postForEntity("http://localhost:8081/realms/dev_duylv/protocol/openid-connect/logout",
                        httpEntity,
                        Response.class);

        Response response = new Response();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            response.setMessage("Logout successful!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {
        HttpHeaders httpHeaders = httpHeaders();
        MultiValueMap<String, String> map = mapUtils();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", request.getToken());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<RefreshTokenResponse> responseEntity = restTemplate
                .postForEntity(tokenUrl, httpEntity, RefreshTokenResponse.class);

        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfileResponse> profile(ProfileTokenRequest request) {

        HttpHeaders httpHeaders = httpHeaders();
        MultiValueMap<String, String> map = mapUtils();
        map.add("token", request.getToken());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<ProfileResponse> responseEntity = restTemplate
                .postForEntity(tokenUrl + "/introspect", httpEntity, ProfileResponse.class);

        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    private MultiValueMap<String, String> mapUtils() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        return map;
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }
}
