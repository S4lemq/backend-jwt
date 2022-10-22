package pl.bykowski.backendjwt.contollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bykowski.backendjwt.dtos.UserDto;
import pl.bykowski.backendjwt.entities.UserEntity;
import pl.bykowski.backendjwt.services.JwtService;
import pl.bykowski.backendjwt.security.AuthRequest;
import pl.bykowski.backendjwt.security.AuthResponse;
import pl.bykowski.backendjwt.services.UserServiceImpl;

@RestController
public class UserApi {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public UserApi(AuthenticationManager authenticationManager, JwtService jwtService, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> getJwt(@RequestBody AuthRequest authRequest){
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            UserEntity userEntity = (UserEntity) authenticate.getPrincipal();
            String token = jwtService.generateJwtToken(userEntity);
            AuthResponse authResponse = new AuthResponse(userEntity.getUsername(), token);
            return ResponseEntity.ok(authResponse);
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
    }
}
