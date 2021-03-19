package com.monto.api.demo.repository.FunctionsRepository;



import com.monto.api.demo.model.Function.Meds;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedsRepository extends MongoRepository<Meds,String> {

    Meds findMedsByMedid(String medid);
    List<Meds> findMedsByStudentname(String studentname);

}
