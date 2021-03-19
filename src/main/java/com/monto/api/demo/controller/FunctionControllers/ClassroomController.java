package com.monto.api.demo.controller.FunctionControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Attendance;
import com.monto.api.demo.model.Function.Classroom;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.repository.FunctionsRepository.AttendanceRepository;
import com.monto.api.demo.repository.FunctionsRepository.ClassroomRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("class")
@RestController
@CrossOrigin("*")
public class ClassroomController {

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

  @GetMapping("createclass")
    public ResponseEntity<?> createclass(@RequestParam int number) {

      classroomRepository.deleteAll();

      List<Student> attendanceList = studentRepository.findAll();
      int presentno = attendanceList.size();

      int clsrm = 1;

      for (int i = 0; i < presentno; i++) {
          if (clsrm == number+1) {
              clsrm = 1;
          }

          Classroom classroom = new Classroom("class" + clsrm, attendanceList.get(i).getStudentname());
          classroomRepository.save(classroom);
          clsrm++;
      }
      return new ResponseEntity<>(new ResponseMessage("Class"), HttpStatus.OK);

  }

    @GetMapping("assignteacher")
    ResponseEntity<?> assignteacher(@RequestParam String teachername , @RequestParam String classroom)
    {

                List<Classroom> list = classroomRepository.findByClassname(classroom);

                for(int i = 0 ; i < list.size() ; i++)
                {

                    Classroom classa = list.get(i);
                    classa.setTeachername(teachername);
                    classroomRepository.save(classa);

                }
                return new ResponseEntity<>(new ResponseMessage("teacher assigned"), HttpStatus.OK);
    }


            @GetMapping("/findall")
            List<Classroom> findall()
            {
                return classroomRepository.findAll();
            }


     @GetMapping("findbyclass")
    List<Classroom> findbyteachername(@RequestParam String username)
     {
        return classroomRepository.findClassroomByTeachername(username);

     }
}
