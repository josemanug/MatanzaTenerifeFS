package com.MatanzaTenerifeFS.backend.Entidades.Role.Repository;

import com.MatanzaTenerifeFS.backend.Entidades.Role.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    boolean existsByName(String name);
}
