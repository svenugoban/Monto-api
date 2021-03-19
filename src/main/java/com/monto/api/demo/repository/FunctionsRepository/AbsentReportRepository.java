package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.AbsentReports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsentReportRepository extends MongoRepository<AbsentReports,String> {

    AbsentReports findByStudentname(String studentname);


}
