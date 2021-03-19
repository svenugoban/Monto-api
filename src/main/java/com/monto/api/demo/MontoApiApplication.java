package com.monto.api.demo;

import com.monto.api.demo.model.userModel.*;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.ConfirmationTokenRepository;
import com.monto.api.demo.repository.UserRepositories.RoleRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
//lol
@SpringBootApplication
public class MontoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MontoApiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder encoder
    ,AndroidUserRepository androidUserRepository ) {

        return args -> {

            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(newAdminRole);
            }

            Role teacherRole = roleRepository.findByName(RoleName.ROLE_PARENT);
            if (teacherRole == null) {
                Role newUserRole = new Role();
                newUserRole.setName(RoleName.ROLE_PARENT);
                roleRepository.save(newUserRole);
            }

            Role parentRole = roleRepository.findByName(RoleName.ROLE_TEACHER);
            if(parentRole == null ){
                Role newParentRole = new Role();
                newParentRole.setName(RoleName.ROLE_TEACHER);
                roleRepository.save(newParentRole);
            }

            Optional<User> user = userRepository.findByUsername("Admin");
            if(!user.isPresent()){

                User adminuser = new User("Admin","admin@aaa.com",encoder.encode("adminpassword"));
                Set<Role> roles = new HashSet<>();

                Role aadminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
                roles.add(aadminRole);
                adminuser.setRoles(roles);

                AndroidUser androidUser = new AndroidUser("admin@aaa.com","adminpassword","admin","Admin");
                androidUserRepository.save(androidUser);

                ConfirmationToken confirmationToken = new ConfirmationToken(adminuser);
                confirmationTokenRepository.save(confirmationToken);

                userRepository.save(adminuser);
            }
        };










    }


}
