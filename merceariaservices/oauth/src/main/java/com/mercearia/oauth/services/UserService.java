package com.mercearia.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mercearia.oauth.feignclients.UserFeignClient;
import com.mercearia.oauth.model.User;

@Service
public class UserService implements UserDetailsService{

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByLogin(String login) {
        System.out.println("\nchegou aqui!!!\n");
        User user = userFeignClient.findByLogin(login).getBody();
        if (user == null) {
            logger.error("Login not found: " + login);
            throw new IllegalArgumentException("Login not found");
        }
        logger.info("Login found: " + login);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByLogin(username).getBody();
		if (user == null) {
			logger.error("Login not found: " + username);
			throw new UsernameNotFoundException("Login not found");
		}
		logger.info("Login found: " + username);
		return user;
    }

    public User save(User user) {
        return userFeignClient.salvar(user).getBody();
    }
    
}
