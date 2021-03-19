package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Attendance;
import com.monto.api.demo.model.Function.Classroom;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.AttendanceRepository;
import com.monto.api.demo.repository.FunctionsRepository.ClassroomRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Attendance")
public class AttendanceController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @RequestMapping("markattendance")
    public ResponseEntity<?> presentstudent(@RequestBody Attendance attendance)
    {
        Attendance student = attendanceRepository.findByStudentname(attendance.getStudentname());

        if(student == null)
        {
            return new ResponseEntity<>(new ResponseMessage("Student name is not in list"), HttpStatus.BAD_REQUEST);

        }
        else {
            Attendance attendance1 = attendanceRepository.findByStudentname(attendance.getStudentname());
            attendance1.setPresent(attendance.isPresent());
            attendanceRepository.save(attendance1);

            return new ResponseEntity<>(new ResponseMessage("Attendance Marked"),HttpStatus.OK);
        }
    }

    @RequestMapping("add_student_to")
    public ResponseEntity<?> Addstudent()
    {
         attendanceRepository.deleteAll();

        List<Student> students = studentRepository.findAll();

        for(int i=0 ; i<= students.size()-1 ; i++)
        {
            String name = students.get(i).getStudentname();

            Classroom classroom = classroomRepository.findClassroomByStudentname(name);

            Attendance attendance = new Attendance();
            attendance.setStudentname(name);
            attendance.setTeachername(classroom.getTeachername());
            attendanceRepository.save(attendance);
        }
        return new ResponseEntity<>(new ResponseMessage(""),HttpStatus.OK);


    }


    @RequestMapping("findall")
    public List<Attendance> findall(@RequestParam String teachername)
    {
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByTeachername(teachername);
        return  attendanceList;

    }




}
