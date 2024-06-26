package org.example.demo_keycloak.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Slf4j
@FieldDefaults(makeFinal = true)
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    String ROLES = "roles";
    String REALM_ACCESS = "realm_access";

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> roles = authorityCollection(jwt);
        return new JwtAuthenticationToken(jwt, roles);
    }

    private Collection<GrantedAuthority> authorityCollection(Jwt jwt) {
        if (jwt.getClaim("realm_access") == null) {
            return Set.of();
        }

        Map<String, Object> realmAccess = jwt.getClaim(REALM_ACCESS);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> keyCloakRoles = objectMapper.convertValue(realmAccess.get(ROLES), new TypeReference<List<String>>() {
        });
        List<GrantedAuthority> roles = new ArrayList<>();
        for (String keycloakRole : keyCloakRoles) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + keycloakRole));
        }
        return roles;

    }
}
