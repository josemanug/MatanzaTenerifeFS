package com.MatanzaTenerifeFS.backend.Entidades.User.Repository;

import com.MatanzaTenerifeFS.backend.Entidades.User.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
