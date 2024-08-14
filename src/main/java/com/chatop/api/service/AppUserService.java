package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chatop.api.dto.LoginDto;
import com.chatop.api.dto.RegisterDto;
import com.chatop.api.model.AppUser;
import com.chatop.api.repository.AppUserRepository;
import com.chatop.api.responses.TokenResponse;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        UserDetails user = User.withUsername(appUser.getEmail()).password(appUser.getPassword()).build();
        return user;
    }

    public TokenResponse createUser(RegisterDto registerDto) {
        AppUser newAppUser = new AppUser();
        newAppUser.setEmail(registerDto.getEmail());
        newAppUser.setName(registerDto.getName());
        newAppUser.setPassword(new BCryptPasswordEncoder().encode(registerDto.getPassword()));
        appUserRepository.save(newAppUser);

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword()));

        String jwtToken = jwtService.generateToken(newAppUser);
        return new TokenResponse(jwtToken);
    }

    public TokenResponse connectUser(LoginDto loginDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        AppUser appUser = appUserRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(appUser);
        return new TokenResponse(jwtToken);
    }
}