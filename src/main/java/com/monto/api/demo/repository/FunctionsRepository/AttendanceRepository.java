package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance,String> {

    Attendance findByStudentname(String studentname);
    List<Attendance> findAttendanceByTeachername(String teachername);



}
