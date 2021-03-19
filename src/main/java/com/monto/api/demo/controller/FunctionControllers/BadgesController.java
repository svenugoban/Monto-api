package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Badges;
import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.BadgesRepository;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("badges")
@CrossOrigin("*")
public class BadgesController {

    @Autowired
    BadgesRepository badgesRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/add")
    public ResponseEntity<?> addbadges(@RequestParam String studentname, @RequestParam String batch,@RequestParam String date, @RequestParam String time,@RequestParam String sender)
    {

        Badges badges = new Badges(batch,studentname,date,time,sender);
        badgesRepository.save(badges);

        AndroidUser androidUser = androidUserRepository.findByUsername(sender);
        String weburl = androidUser.getWebprofileurl();
        String andridurl = androidUser.getAndroidurl();
        String extrmessage = "  Badge Received by your Child";
        Notification notification = new Notification(sender,batch+extrmessage,studentname,date,time,weburl,andridurl);
        notificationRepository.save(notification);
        return new ResponseEntity<>(new ResponseMessage("Badge added"), HttpStatus.OK);
    }

    @GetMapping("find")
    public List<Badges> findbystudentname(@RequestParam  String parentusernam)
    {
        Student student = studentRepository.findByParentUsername(parentusernam);

        List<Badges> list = badgesRepository.findByStudentname(student.getStudentname());
        return list;
    }


}
