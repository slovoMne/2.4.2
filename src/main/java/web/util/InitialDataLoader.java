package web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
//import web.service.Users_RolesService;

@Component
public class InitialDataLoader {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostConstruct
    public void initData() {
        System.out.println("Postconstruct method!!!");

        Role role = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        User user = new User("user", "100", "user@gmail.com", (byte) 20);
        User user2 = new User("admin", "100", "admin@gmail.com", (byte) 30);

        user.addRoleToUser(role);
        user2.addRoleToUser(role2);
        userService.saveUser(user);
        userService.saveUser(user2);
    }
}