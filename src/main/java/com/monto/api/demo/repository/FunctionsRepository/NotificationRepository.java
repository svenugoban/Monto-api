package com.monto.api.demo.repository.FunctionsRepository;

import com.monto.api.demo.model.Function.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository  extends MongoRepository<Notification , String> {

    List<Notification> findByStudentname(String studentname);



}
