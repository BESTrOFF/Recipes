package com.bystrov.recipes5.service;

import com.bystrov.recipes5.entity.user.Role;
import com.bystrov.recipes5.entity.user.User;
import com.bystrov.recipes5.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByName(String name) {

        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    public boolean save(User user) {
        User userForCheck = userRepository.findByName(user.getName());

        if (userForCheck != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<User> userGraterThenList(Integer minId) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class).setParameter("paramId", minId).getResultList();
    }
}
