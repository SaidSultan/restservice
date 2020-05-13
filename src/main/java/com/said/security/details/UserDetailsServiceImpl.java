package com.said.security.details;


import com.said.model.User;
import com.said.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
/*    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
/*        UserDetails user =  new UserDetailsImpl(userDao.getUserByLogin(login));
        if (user == null) {
            throw new UsernameNotFoundException("юзера не существует");
        }
        return user;*/
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("юзера не существует");
        }
        return user;
    }
}
