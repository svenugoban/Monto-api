package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Homework;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeworkRepository extends MongoRepository<Homework,String > {


}
