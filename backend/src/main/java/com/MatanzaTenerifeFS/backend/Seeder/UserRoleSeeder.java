package com.MatanzaTenerifeFS.backend.Seeder;

import com.MatanzaTenerifeFS.backend.Entidades.Role.Interfaces.IRoleService;
import com.MatanzaTenerifeFS.backend.Entidades.Role.Models.Role;
import com.MatanzaTenerifeFS.backend.Entidades.User.Interfaces.IUserService;
import com.MatanzaTenerifeFS.backend.Entidades.User.Models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRoleSeeder implements CommandLineRunner {

    private final IUserService userService;
    private final IRoleService roleService;

    public UserRoleSeeder(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRole();
        seedUser();
    }

    private void seedRole() {

        if(!roleService.existsByName("ROLE_ADMIN")){
            roleService.createRole(new Role("ROLE_ADMIN"));
        }

        if(!roleService.existsByName("ROLE_SUPERVISOR")){
            roleService.createRole(new Role("ROLE_SUPERVISOR"));
        }
    }

    private void seedUser() {

        if(!userService.existsUser("joseglez")){
            User user = new User(
                    "joseglez",
                    "jose123!",
                    roleService.getAllRole("ROLE_ADMIN")
            );
            userService.createUser(user);
        }

        if(!userService.existsUser("nikorguez")){
            User user = new User(
                    "nikorguez",
                    "niko123!",
                    roleService.getAllRole("ROLE_SUPERVISOR")
            );
            userService.createUser(user);
        }
    }
}
