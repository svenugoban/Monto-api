package com.monto.api.demo.controller.UserControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.ClassroomRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("student")
@CrossOrigin("*")
public class StudentControler {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @PostMapping("/add")
    public ResponseEntity<?> Addmstudent(@RequestBody Student student)
    {

        if(!studentRepository.existsByStudentid(student.getStudentid()) && !studentRepository.existsByStudentname(student.getStudentname())) {
            Student studentsave = new Student(student.getStudentid(), student.getStudentname(), student.getAddress(), student.getDob(), student.getSex());
            studentRepository.save(studentsave);

           Student savedstudent = studentRepository.findByStudentid(student.getStudentid());
            String GenerateCode = savedstudent.getId();

            return new ResponseEntity<>(new ResponseMessage(GenerateCode), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Student Id is Existing OR Student name is existing"),
                HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/findone")
    public Student findonestudent(@RequestBody Student student)
    {

        Student ssst = studentRepository.findByStudentname(student.getStudentname());
        return ssst;

    }


    @RequestMapping("/findonestudent")
    public Student findonestudentbyname(@RequestParam String parentusername)
    {


        Student student = studentRepository.findByParentUsername(parentusername);
        return student;

    }


    @GetMapping("/findallstudent")
    public List<Student> findall()
    {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @PostMapping("/imageupload")
    public ResponseEntity<?> imgeupload(@RequestParam("file") MultipartFile file, @RequestParam String parentusername) throws IOException {
        Student student = studentRepository.findByParentUsername(parentusername);
        if (student != null) {

            File convertFile = new File("C:\\Imagehost\\src\\assets\\students\\"+student.getStudentname()+".jpg");
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();


            student.setProfilepicUrl("http://localhost:4300/assets/students/"+student.getStudentname()+".jpg");
            student.setAndroidurl("http://10.0.2.2:4300/assets/students/"+student.getStudentname()+".jpg");
            studentRepository.save(student);
            return new ResponseEntity<>(new ResponseMessage("Added"),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseMessage("Not Added"),HttpStatus.BAD_REQUEST);
    }


    @GetMapping("studentlist")
    public String[] studenrnames()
    {
        List<Student> students = studentRepository.findAll();

        String[] list = new String[students.size()];

        for(int i=0 ; i<students.size() ; i++)
        {

            Student student = students.get(i);

            list[i] = student.getStudentname();
        }

        return list;

    }



    }


