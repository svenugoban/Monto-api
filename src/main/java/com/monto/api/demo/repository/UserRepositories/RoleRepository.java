package com.monto.api.demo.repository.UserRepositories;

import com.monto.api.demo.model.userModel.Role;
import com.monto.api.demo.model.userModel.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Role findByName(RoleName roleName);

}
