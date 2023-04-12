package com.diplom.second.service;

import com.diplom.second.model.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserAuthorizationService implements UserDetailsService {

    private final CustomUserService customUserService;

    public CustomUserAuthorizationService(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserService.getByLogin(username);
        if (customUser == null) {
            throw new UsernameNotFoundException(username + "not found");
        }
        if (!customUser.isVerified()) {
            return null;
        }
        List<GrantedAuthority> roles = Arrays.asList(
                new SimpleGrantedAuthority(customUser.getRole().toString()));
        return new User(customUser.getLogin(), customUser.getPassword(), roles);
    }
}