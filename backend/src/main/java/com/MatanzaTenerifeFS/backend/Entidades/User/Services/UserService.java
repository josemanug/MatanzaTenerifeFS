package com.MatanzaTenerifeFS.backend.Entidades.User.Services;

import com.MatanzaTenerifeFS.backend.Entidades.User.Interfaces.IUserService;
import com.MatanzaTenerifeFS.backend.Entidades.User.Models.User;
import com.MatanzaTenerifeFS.backend.Entidades.User.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }
}
