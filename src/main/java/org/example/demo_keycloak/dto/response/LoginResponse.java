package org.example.demo_keycloak.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    String access_token;

    String refresh_token;

    String expires_in;

    String refresh_expires_in;

    String token_type;

}
