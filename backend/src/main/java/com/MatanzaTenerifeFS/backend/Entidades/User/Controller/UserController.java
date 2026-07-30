package com.MatanzaTenerifeFS.backend.Entidades.User.Controller;

import com.MatanzaTenerifeFS.backend.Entidades.User.DTOs.LoginRequest;
import com.MatanzaTenerifeFS.backend.Entidades.User.DTOs.LoginResponse;
import com.MatanzaTenerifeFS.backend.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                );

        Authentication authResult =
                authenticationManager.authenticate(authentication);

        UserDetails userDetails =
                (UserDetails) authResult.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token);
    }
}
