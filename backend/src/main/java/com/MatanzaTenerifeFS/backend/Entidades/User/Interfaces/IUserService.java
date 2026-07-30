package com.MatanzaTenerifeFS.backend.Entidades.User.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.User.Models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    void createUser(User user);

    boolean existsUser(String username);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
