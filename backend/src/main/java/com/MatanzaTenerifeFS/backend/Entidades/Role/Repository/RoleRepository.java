package com.MatanzaTenerifeFS.backend.Entidades.Role.Repository;

import com.MatanzaTenerifeFS.backend.Entidades.Role.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    boolean existsByName(String name);

    List<Role> findAllByName(String rolename);
}
