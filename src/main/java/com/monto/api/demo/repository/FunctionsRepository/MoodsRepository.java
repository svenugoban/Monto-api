package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Moods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoodsRepository extends MongoRepository<Moods , String > {




}
