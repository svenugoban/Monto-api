package com.monto.api.demo.repository.UserRepositories;

import com.monto.api.demo.model.userModel.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken,String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
