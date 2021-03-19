package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Moods;
import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.repository.FunctionsRepository.MoodsRepository;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("moods")
@CrossOrigin("*")
public class MoodsController {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @Autowired
    MoodsRepository moodsRepository;


    @GetMapping("/add")
    public ResponseEntity<?> addmoods(@RequestParam String studentname, @RequestParam String mood, @RequestParam String date, @RequestParam String time, @RequestParam String sender)
    {

        Moods moods = new Moods(mood,studentname,date,time,sender);
        moodsRepository.save(moods);

        AndroidUser androidUser = androidUserRepository.findByUsername(sender);
        String weburl = androidUser.getWebprofileurl();
        String andridurl = androidUser.getAndroidurl();

        Notification notification = new Notification(sender,"Your Child mood was "+mood+" Today",studentname,date,time,weburl,andridurl);
        notificationRepository.save(notification);

        return new ResponseEntity<>(new ResponseMessage("Badge added"), HttpStatus.OK);

    }


}
