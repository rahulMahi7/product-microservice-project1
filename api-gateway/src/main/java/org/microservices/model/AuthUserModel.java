package org.microservices.model;

import lombok.*;

import java.util.Collection;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthUserModel {
    private String userId;
    private String accessToken;
    private String RefreshToken;
    private long expiresAt;
    private Collection<String> authorities;
}
