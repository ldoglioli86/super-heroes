package com.superheroes.controller;

import com.superheroes.security.AuthenticationRequest;
import com.superheroes.security.JwtUtilService;
import com.superheroes.security.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping(path = "/authenticate")
    public TokenInfo authenticate(@RequestBody AuthenticationRequest authenticationReq) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsername(),
                        authenticationReq.getPassword()));

        var userDetails = userDetailsService.loadUserByUsername(
            authenticationReq.getUsername());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return tokenInfo;
    }
}
