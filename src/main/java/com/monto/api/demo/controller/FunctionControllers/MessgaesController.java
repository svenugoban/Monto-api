package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Messages;
import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.FunctionsRepository.MessagesRepository;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@CrossOrigin("*")
public class MessgaesController {

    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @PostMapping("add")
    ResponseEntity<?> add (@RequestBody Messages messages)
    {
        messagesRepository.save(messages);

        AndroidUser androidUser = androidUserRepository.findByUsername(messages.getSender());

        String web = androidUser.getWebprofileurl();
        String android = androidUser.getAndroidurl();


        Notification notification = new Notification(messages.getSender(),messages.getMessage(),messages.getStudentname(),messages.getDate(),messages.getTime(),web,android);
        notificationRepository.save(notification);

        return new ResponseEntity<>(new ResponseMessage("Added"), HttpStatus.OK);
    }


    @GetMapping("find")
    List<Messages> find(@RequestParam  String parentusername)
    {
        Student student = studentRepository.findByParentUsername(parentusername);

        List<Messages> messages = messagesRepository.findByStudentname(student.getStudentname());
        return messages;


    }


    @PostMapping("/findone")
    List<Messages> findmessage(@RequestBody Messages messages)
    {
        List<Messages> messages1 = messagesRepository.findByStudentnameAndSender(messages.getStudentname(),messages.getSender());
        return messages1;
    }

}
