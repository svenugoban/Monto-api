package com.monto.api.demo.repository.UserRepositories;


import com.monto.api.demo.model.userModel.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {

    Student findByParentUsername(String parentusername);
    String findStudentByParentUsername(String parentusername);
    Student findStudentById(String id);
    Student findByStudentid(String studentid);
    Student findByStudentname(String studentname);

    boolean existsByStudentname(String studentname);
    boolean existsByStudentid(String id);

    int countByStudentid();

//    @Query(value="{ studentname : ?0}", fields="{ studentname : 0 }")
//    List<Student> findByPath();






}
