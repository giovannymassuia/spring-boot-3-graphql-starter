package io.giovannymassuia.splitappapi.config;

import java.time.Instant;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(CsrfConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .securityContext(context -> context.requireExplicitSave(false))
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  // Commment this bean to use the real JWT decoder from AWS Cognito via
  // the OAuth2 Resource Server config:
  // `issuer-uri: https://cognito-idp.<region>.amazonaws.com/<user-pool-id>`
  public JwtDecoder jwtDecoder() {
    return new JwtDecoder() {
      @Override
      public Jwt decode(String token) throws JwtException {
        // Dummy JWT with fake data and headers
        return new Jwt(token, Instant.now(), Instant.now().plusMillis(60_000),
            Map.of("alg", "RS256"),
            Map.of("sub", "123", "name", "John Doe", "email", "john.doe@email.com"));
      }
    };
  }

}
