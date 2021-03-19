package com.monto.api.demo.repository.UserRepositories;


import com.monto.api.demo.model.userModel.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmailIgnoreCase(String email);
    User findByEmail(String email);


   User findByRoles(String role);




}
