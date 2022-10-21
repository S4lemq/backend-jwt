package pl.bykowski.backendjwt.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bykowski.backendjwt.JwtService;

@RestController
public class UserApi {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserApi(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> getJwt(@RequestBody AuthRequest authRequest){
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            MyUser myUser = (MyUser) authenticate.getPrincipal();
            String token = jwtService.generateJwtToken(myUser);
            AuthResponse authResponse = new AuthResponse(myUser.getUsername(), token);
            return ResponseEntity.ok(authResponse);
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
