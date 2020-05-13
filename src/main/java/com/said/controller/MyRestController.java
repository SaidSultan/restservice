package com.said.controller;

import com.said.model.User;
import com.said.service.RoleService;
import com.said.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class MyRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("all")
    public List<User> userList() {
        return userService.getListUsers();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user;
    }

}
