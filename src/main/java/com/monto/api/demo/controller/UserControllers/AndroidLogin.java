package com.monto.api.demo.controller.UserControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Badges;
import com.monto.api.demo.model.Function.NewsFeedUpload;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.Student;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.FunctionsRepository.BadgesRepository;
import com.monto.api.demo.repository.FunctionsRepository.NewsFeedRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidLoginRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.StudentRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("androidlogin")
@CrossOrigin("*")
public class AndroidLogin {

    @Autowired
    AndroidLoginRepository androidLoginRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BadgesRepository badgesRepository;

    @Autowired
    NewsFeedRepository newsFeedRepository;

    @GetMapping("/login")
    public AndroidUser login(@RequestParam String username , @RequestParam String password)
    {

        AndroidUser androidUser1 = androidLoginRepository.findByUsername(username);

        if(androidUser1 == null)
            {
                return null;
            }
        else
            {
                if(androidUser1.getPassword().equals(password))
                    {
                        if(androidUser1.getUsertype().equals("parent"))
                        {
                            Student student = studentRepository.findByParentUsername(androidUser1.getUsername());
                            List<Badges> badges = badgesRepository.findByStudentname(student.getStudentname());
                            if (badges.size() >= 5) {
                                NewsFeedUpload newsFeedUpload = newsFeedRepository.findByName(student.getStudentname() + "badge");
                                if (newsFeedUpload == null) {
                                    NewsFeedUpload newsFeedUpload1 = new NewsFeedUpload(student.getStudentname() + "badge", student.getStudentname() + " Got 5 badges continously",
                                            "http://localhost:4300/assets/imgs/badge.jpg", "http://10.0.2.2:4300/assets/imgs/badge.jpg");
                                    newsFeedRepository.save(newsFeedUpload1);
                                }
                            }

                        }


                        return androidUser1;
                    }
                else
                    {
                        return null;
                    }
            }


    }

    @GetMapping("changepassword")
    public ResponseEntity<?> changepassword(@RequestParam String username) {

        Optional<User> user = userRepository.findByUsername(username);

        User user1 = user.get();

        return new ResponseEntity<>(new ResponseMessage(user1.getUsername()),HttpStatus.OK);

    }

    @RequestMapping("find")
    AndroidUser finduser(@RequestParam String username)
    {
        return androidUserRepository.findByUsername(username);
    }
}
