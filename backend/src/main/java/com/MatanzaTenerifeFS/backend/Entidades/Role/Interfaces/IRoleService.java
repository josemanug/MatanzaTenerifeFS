package com.MatanzaTenerifeFS.backend.Entidades.Role.Interfaces;

import com.MatanzaTenerifeFS.backend.Entidades.Role.Models.Role;

public interface IRoleService {

    Role findByRolename(String name);

    void createRole(Role role);

    boolean existsByName(String name);
}
