package com.said.service;


import com.said.model.User;
import com.said.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setUserDao( PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        String password;
        User updateUser = getUserById(user.getId());
        if(user.getPassword().equals("")) {
            password = updateUser.getPassword();
        } else {
            password = passwordEncoder.encode(user.getPassword());
        }
        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
        updateUser.setNailsColor(user.getNailsColor());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(password);
        updateUser.setRoles(user.getRoles());
    }

/*    @Modifying
    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
    public void updateUser(User user) {
        String password;
        User updateUser = getUserById(user.getId());
        if(user.getPassword().equals("")) {
            password = updateUser.getPassword();
        } else {
            password = passwordEncoder.encode(user.getPassword());
        }
    }*/

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {

        return userRepository.findByLogin(login);
    }


/*    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }*/
}
