package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassroomRepository extends MongoRepository<Classroom,String> {



    List<Classroom> findByClassname(String classname);
    Classroom findClassroomByStudentname(String studenname);
    List<Classroom> findClassroomByTeachername(String username);
}
