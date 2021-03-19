package com.monto.api.demo.controller.UserControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.TeacherInfo;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.UserRepositories.AndroidLoginRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.TeacherInfoRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AndroidLoginRepository androidLoginRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping("/teacher")
    public List<TeacherInfo> getTeacher()
    {
        List<TeacherInfo> teacher = teacherInfoRepository.findAll();
        return teacher;
    }

@RequestMapping("/resetpassword")
    public ResponseEntity<?> resetpassword(@RequestParam String username,@RequestParam String newpassword)
{
    Optional<User> user1 = userRepository.findByUsername(username);
    if(user1.isPresent()) {
        User userfind = user1.get();

        AndroidUser androidUser = androidUserRepository.findByUsername(userfind.getUsername());
        {
            userfind.setPassword(passwordEncoder.encode(newpassword));
            androidUser.setPassword(newpassword);
            androidUserRepository.save(androidUser);
            userRepository.save(userfind);
            //userRepository.delete(user1);
            return new ResponseEntity<>(new ResponseMessage("Password Changed"), HttpStatus.OK);
        }
        }
    else
        return new ResponseEntity<>(new ResponseMessage("No user in thatname"),HttpStatus.BAD_REQUEST);

}


}
