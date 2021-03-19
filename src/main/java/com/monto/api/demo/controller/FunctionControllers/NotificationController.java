package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notification")
@CrossOrigin("*")
public class NotificationController {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/find")
    public List<Notification> findnotification(@RequestParam String parentusername)
    {
        Student student = studentRepository.findByParentUsername(parentusername);

        return notificationRepository.findByStudentname(student.getStudentname());

    }


}
