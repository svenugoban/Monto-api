package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Observation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObservationRepository extends MongoRepository<Observation,String> {


}
