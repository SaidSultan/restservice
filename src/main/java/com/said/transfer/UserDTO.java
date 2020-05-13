package com.said.transfer;

import com.said.model.Role;
import com.said.model.User;

import java.util.Set;

public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String nailsColor;
    private Set<Role> roles;

    public UserDTO(int id, String name, String lastName, String nailsColor, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nailsColor = nailsColor;
        this.roles = roles;
    }

    public static UserDTO from(User user){
        return new UserDTO(user.getId(), user.getName(), user.getLastName(), user.getNailsColor(), user.getRoles());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNailsColor() {
        return nailsColor;
    }

    public void setNailsColor(String nailsColor) {
        this.nailsColor = nailsColor;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
