package com.monto.api.demo.repository.FunctionsRepository;


import com.monto.api.demo.model.Function.Badges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgesRepository extends MongoRepository<Badges,String> {

    Badges findBadgesByBadgename(String badgename);

    List<Badges> findByStudentname(String studentname);


}
