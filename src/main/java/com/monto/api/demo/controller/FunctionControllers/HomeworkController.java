package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Homework;
import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.repository.FunctionsRepository.HomeworkRepository;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("homework")
@CrossOrigin("*")
public class HomeworkController {

    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    NotificationRepository notificationRepository;


    @Autowired
    AndroidUserRepository androidUserRepository;

    @PostMapping("add")
    ResponseEntity<?> add_homework(@RequestBody Homework homework)
    {
        homeworkRepository.save(homework);

        AndroidUser androidUser = androidUserRepository.findByUsername(homework.getTeachername());
        String weburl = androidUser.getWebprofileurl();
        String andridurl = androidUser.getAndroidurl();
        Notification notification = new Notification(homework.getTeachername(),homework.getDescription(),"",homework.getDate(),homework.getTime(),weburl,andridurl);
        notificationRepository.save(notification);

        return new ResponseEntity<>(new ResponseMessage("Homework Addedd"), HttpStatus.OK);
    }


}
