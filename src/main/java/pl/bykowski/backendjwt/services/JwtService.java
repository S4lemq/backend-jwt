package pl.bykowski.backendjwt.services;

import com.auth0.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.bykowski.backendjwt.security.JwtConfig;
import pl.bykowski.backendjwt.entities.UserEntity;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateJwtToken(UserEntity userEntity){
        return JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getMillisecondsInDay()))
                .withIssuer(jwtConfig.getIssuer())
                .withClaim("role", userEntity.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(jwtConfig.getSecretKey());
    }
}
