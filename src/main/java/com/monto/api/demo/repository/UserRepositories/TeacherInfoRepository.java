package com.monto.api.demo.repository.UserRepositories;

import com.monto.api.demo.model.userModel.TeacherInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface TeacherInfoRepository extends MongoRepository<TeacherInfo,String> {

  //  List<TeacherInfo>
    TeacherInfo findTeacherInfoById(String id);
    TeacherInfo findTeacherInfoByTeacherusername(String username);
    TeacherInfo findByTeacherid(String id);

}
