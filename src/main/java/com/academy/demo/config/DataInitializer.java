package com.academy.demo.config;

import com.academy.demo.persistance.entity.Role;
import com.academy.demo.persistance.entity.User;
import com.academy.demo.persistance.repository.RoleRepository;
import com.academy.demo.persistance.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * THIS CLASS INITIALIZES THE DEFAULT USERS
 * !!! NOT NEEDED - JUST FOR THE TEST EXAMPLE
 */
@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.getRoles().add(adminRole);
            userRepository.save(admin);

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.getRoles().add(userRole);
            userRepository.save(user);
        };
    }
}
