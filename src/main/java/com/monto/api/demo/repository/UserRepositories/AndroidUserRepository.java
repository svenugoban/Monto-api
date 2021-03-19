package com.monto.api.demo.repository.UserRepositories;

import com.monto.api.demo.model.userModel.AndroidUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AndroidUserRepository extends MongoRepository<AndroidUser , String> {

        AndroidUser findByEmail(String email);
        AndroidUser findByUsername(String username);



}
