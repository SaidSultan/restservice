package com.said.service;

import com.said.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void addRole(Role role);
    Role getRoleById(int id);
    Role getRoleByName(String name);
    void updateRole(Role role);
    void deleteRoleById(int id);
}
