package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncidentRepository extends MongoRepository<Incident,String > {


}
