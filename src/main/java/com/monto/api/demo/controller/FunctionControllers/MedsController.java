package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Meds;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.MedsRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meds")
@CrossOrigin("*")
public class MedsController {

    @Autowired
    private MedsRepository medsRepository;

    @Autowired
    private StudentRepository studentRepository;


    @PostMapping("/add")
    public ResponseEntity<?> Addmeds(@RequestBody  Meds meds)
    {

        Meds meds1 =new Meds(meds.getStudentname(),meds.getTime(),meds.getDate(),meds.getIncident(),meds.getFirstaid(),meds.getMedid());
        medsRepository.save(meds1);
        return new ResponseEntity<>(new ResponseMessage("Meds Info Added"), HttpStatus.OK);
    }

@RequestMapping("/findone")
    public List<Meds> findmeds(@RequestParam  String parentusername)
    {
        Student student = studentRepository.findByParentUsername(parentusername);

                String studnetname = student.getStudentname();
        List<Meds> onemed;
        onemed=  medsRepository.findMedsByStudentname(studnetname);

        return onemed;
    }


}
