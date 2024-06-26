package org.example.demo_keycloak.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    String scope;
    String email_verified;
    String name;
    String preferred_username;
    String given_name;
    String family_name;
    String email;
    String username;
    String active;

}
