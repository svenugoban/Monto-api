package com.monto.api.demo.repository.FunctionsRepository;


import com.monto.api.demo.model.Function.NewsFeedUpload;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsFeedRepository extends MongoRepository<NewsFeedUpload, String> {

    NewsFeedUpload findByName(String name);


}
