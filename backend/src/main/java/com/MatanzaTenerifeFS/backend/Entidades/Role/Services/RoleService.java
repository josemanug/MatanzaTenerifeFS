package com.MatanzaTenerifeFS.backend.Entidades.Role.Services;

import com.MatanzaTenerifeFS.backend.Entidades.Role.Interfaces.IRoleService;
import com.MatanzaTenerifeFS.backend.Entidades.Role.Models.Role;
import com.MatanzaTenerifeFS.backend.Entidades.Role.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Devuelve un rol en función del nombre
    public Role findByRolename(String name){
        return roleRepository.findByName(name);
    }

    // Crear un nuevo rol
    public void createRole(Role role){
        roleRepository.save(role);
    }

    // Comprobar si existe un rol
    public boolean existsByName(String name){
        return roleRepository.existsByName(name);
    }
}
