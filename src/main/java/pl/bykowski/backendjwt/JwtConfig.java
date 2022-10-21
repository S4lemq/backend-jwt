package pl.bykowski.backendjwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
    private final String secret;
    private final Integer millisecondsInDay;
    private final Integer millisecondsInWeek;
    private final String tokenPrefix;
    private final String issuer;

    public JwtConfig(@Value("${jwt.secret}") String secret,
                     @Value("${jwt.millisecondsInDay}") Integer millisecondsInDay,
                     @Value("${jwt.millisecondsInWeek}") Integer millisecondsInWeek,
                     @Value("${jwt.tokenPrefix}")String tokenPrefix,
                     @Value("${jwt.issuer}")String issuer) {
        this.secret = secret;
        this.millisecondsInDay = millisecondsInDay;
        this.millisecondsInWeek = millisecondsInWeek;
        this.tokenPrefix = tokenPrefix;
        this.issuer = issuer;
    }

    public Algorithm getSecretKey(){
        return Algorithm.HMAC256(secret.getBytes());
    }

    public Integer getMillisecondsInDay() {
        return millisecondsInDay;
    }

    public Integer getMillisecondsInWeek() {
        return millisecondsInWeek;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getIssuer() {
        return issuer;
    }
}
