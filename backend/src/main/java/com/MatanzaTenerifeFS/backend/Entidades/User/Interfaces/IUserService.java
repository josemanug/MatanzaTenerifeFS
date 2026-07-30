package com.MatanzaTenerifeFS.backend.Entidades.User.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.User.Models.User;

public interface IUserService {

    void createUser(User user);

    boolean existsUser(String username);
}
