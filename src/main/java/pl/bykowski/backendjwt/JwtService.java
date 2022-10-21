package pl.bykowski.backendjwt;

import com.auth0.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.bykowski.backendjwt.user.MyUser;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateJwtToken(MyUser myUser){
        return JWT.create()
                .withSubject(myUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getMillisecondsInDay()))
                .withIssuer(jwtConfig.getIssuer())
                .withClaim("role", myUser.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(jwtConfig.getSecretKey());
    }
}
