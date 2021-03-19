package com.monto.api.demo.controller.UserControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/parent")
@RestController
@CrossOrigin("*")
public class ParentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("connectparent")
    public ResponseEntity<?> connect(@RequestParam String generatecode , @RequestParam String username)
    {

        Student student = studentRepository.findStudentById(generatecode);
        student.setParentUsername(username);
        studentRepository.save(student);

        return new ResponseEntity<>(new ResponseMessage("Parent Connection added"), HttpStatus.OK);

    }

}
