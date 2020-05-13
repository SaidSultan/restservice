package com.said.service;


import com.said.model.Role;
import com.said.model.User;
import com.said.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

/*    @Override
    public Role getRoleByName(String name) {
        return
                roleDao.getRoleByName(name);
    }*/

    @Override
    public void updateRole(Role role) {
        Role updateRole = getRoleById(role.getId());
        updateRole.setId(role.getId());
        updateRole.setName(role.getName());
    }

    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }
}
