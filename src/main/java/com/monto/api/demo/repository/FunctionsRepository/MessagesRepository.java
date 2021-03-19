package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Messages,String > {

    List<Messages> findByStudentname(String studentname);

    List<Messages> findByStudentnameAndSender(String studentname , String sender);

}
