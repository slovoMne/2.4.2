package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {

    Role findByRolename(String name);

    void saveRole(Role role);

    List<Role> getAllRoles();
}