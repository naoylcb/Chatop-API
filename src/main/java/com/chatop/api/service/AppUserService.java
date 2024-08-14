package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chatop.api.model.AppUser;
import com.chatop.api.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        UserDetails user = User.withUsername(appUser.getEmail()).password(appUser.getPassword()).build();
        return user;
    }
}