package com.said.controller;

import com.said.model.Role;
import com.said.model.User;
import com.said.service.RoleService;
import com.said.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

/*    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping(value = "/admin")
    public String listUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("authUser", user);
        model.addAttribute("userList", userService.getListUsers());
        return "test";
    }
    @GetMapping(value = "/EditUser")
    public String EditUser(@RequestParam("userId") int userId, Model model) {
/*        User usr = userService.getUserById(userId);*/
        model.addAttribute("userId", userId);
        return "modalEditUser";
    }


/*    @GetMapping("/user")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userService.getListUsers());
        return modelAndView;
    }*/

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("age") String age,
                          @RequestParam("nailsColor") String nailsColor,
                          @RequestParam("login") String login,
                          @RequestParam("password") String pass,
                          @RequestParam("useRole") String[] roles) {
        Set<Role> roleSet = new LinkedHashSet<>();
        for (String roleName : roles) {
            roleSet.add(roleService.getRoleByName(roleName));
            System.out.println(roleService.getRoleByName(roleName) + " ROLES ROLES ROLES!!!");
        }
        User user = new User(name, lastName, Integer.parseInt(age), nailsColor, login, pass, roleSet);
        userService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.GET)
    public String editUser(@RequestParam("editBtn") String id, Model model) {
        model.addAttribute("user", userService.getUserById(Integer.parseInt(id)));
        return "editUser";
    }

/*    @RequestMapping(value = "/admin/doEdit", method = RequestMethod.POST)
    public String doEdit(@RequestParam("id") String id,
                         @RequestParam("name") String name,
                         @RequestParam("lastname") String lastName,
                         @RequestParam("age") String age,
                         @RequestParam("nailsColor") String nailsColor,
                         @RequestParam("login") String login,
                         @RequestParam("password") String pass,
                         @RequestParam("useRole") String[] role*//*, @ModelAttribute User user*//*) {
        Set<Role> roles = new LinkedHashSet<>();
        for (String roleName : role) {
            roles.add(roleService.getRoleByName(roleName));
        }
        User user = new User(Integer.parseInt(id), name, lastName, Integer.parseInt(age), nailsColor, login, pass, roles);
*//*        User updateUser = new User(user.getId(), user.getName(), user.getLastName(), user.getAge(), user.getNailsColor(), user.getLogin(),
                user.getPassword(), roles);*//*
        userService.updateUser(user);
        //userService.updateUser(updateUser);
        return "redirect:/admin";
    }*/

    @PostMapping("/admin/doEdit")
    public String doEdit(User user, @RequestParam("useRole") String[] role) {
        Set<Role> roles = new LinkedHashSet<>();
        for (String roleName : role) {
            roles.add(roleService.getRoleByName(roleName));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam("deleteBtn") String id) {
        userService.deleteUserById(Integer.parseInt(id));
        return "redirect:/admin";
    }
}
