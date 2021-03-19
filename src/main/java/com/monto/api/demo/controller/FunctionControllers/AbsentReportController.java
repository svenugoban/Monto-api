package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.AbsentReports;
import com.monto.api.demo.model.Function.Attendance;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.AbsentReportRepository;
import com.monto.api.demo.repository.FunctionsRepository.AttendanceRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/absent-report")
@CrossOrigin("*")
public class AbsentReportController {

    @Autowired
    AbsentReportRepository absentReportRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("absent")
    public ResponseEntity<?> absentreport(@RequestBody AbsentReports absentReports)
    {
        Student student = studentRepository.findByParentUsername(absentReports.getParentusername());

        Attendance attendance = attendanceRepository.findByStudentname(student.getStudentname());

        if(attendance!=null)
        {
            AbsentReports absentReport = new AbsentReports(student.getStudentname(),absentReports.getAbsentdate(), absentReports.getReason());
            absentReportRepository.save(absentReport);

            Attendance attendance1 = attendanceRepository.findByStudentname(student.getStudentname());
            attendanceRepository.delete(attendance1);
            return new ResponseEntity<>(new ResponseMessage("Absent Report Added"),
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseMessage("Already Report Added"),HttpStatus.BAD_REQUEST);


    }


}
