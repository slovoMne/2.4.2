package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    public Role findByRolename(String name);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
