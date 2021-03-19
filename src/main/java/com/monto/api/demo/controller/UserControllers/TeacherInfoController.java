package com.monto.api.demo.controller.UserControllers;

import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.TeacherInfo;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.TeacherInfoRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.KeyStore;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacherinfo")
@CrossOrigin("*")
public class TeacherInfoController {


    @Autowired
   private TeacherInfoRepository teacherInfoRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("add")
    public ResponseEntity<?> addteacherinfo(@RequestBody TeacherInfo teacherInfo)
    {
        TeacherInfo teacherInfo2 = teacherInfoRepository.findByTeacherid(teacherInfo.getTeacher_id());
                if(teacherInfo2 == null) {
                    TeacherInfo teacherInfosave = new TeacherInfo(teacherInfo.getTeacher_id(), teacherInfo.getTeacher_name(), teacherInfo.getDob(), teacherInfo.getGender(), teacherInfo.getAddress(), teacherInfo.getQualification());
                    teacherInfoRepository.save(teacherInfosave);
                    TeacherInfo teacherInfo1 = teacherInfoRepository.findByTeacherid(teacherInfo.getTeacher_id());
                    String generate = teacherInfo1.getId();

                    return new ResponseEntity<>(new ResponseMessage(generate), HttpStatus.OK);
                }else
                    return new ResponseEntity<>(new ResponseMessage("Teacher is Duplicated"), HttpStatus.OK);
    }

    @RequestMapping("/findall")
    public List<TeacherInfo> finall()
    {
        List<TeacherInfo> list = teacherInfoRepository.findAll();
        return list;

    }

    @RequestMapping("/connect")
    ResponseEntity<?> connectinfo(@RequestParam String generatecode, @RequestParam String username)
    {
        AndroidUser androidUser = androidUserRepository.findByUsername(username);
        String username_user = androidUser.getUsername();
        TeacherInfo teacherInfo = teacherInfoRepository.findTeacherInfoById(generatecode);
        teacherInfo.setTeacherusername(username_user);
        teacherInfoRepository.save(teacherInfo);

        return new ResponseEntity<>(new ResponseMessage("Connected"),HttpStatus.OK);
    }

    @RequestMapping("/edit")
    ResponseEntity<?> editprofile(@RequestParam String address , @RequestParam String username)
    {
        TeacherInfo teacherInfo1 = teacherInfoRepository.findTeacherInfoByTeacherusername(username);
        teacherInfo1.setAddress(address);
        teacherInfoRepository.save(teacherInfo1);

        return new ResponseEntity<>(new ResponseMessage("Edited"),HttpStatus.OK);
    }

    @RequestMapping("/changepassword")
    ResponseEntity<?> changepassword(@RequestParam String oldpass , @RequestParam String newpass , @RequestParam String username)
    {

        Optional<User> user1 = userRepository.findByUsername(username);
       if(user1.isPresent()) {
           User userfind = user1.get();

        AndroidUser androidUser = androidUserRepository.findByUsername(userfind.getUsername());
        if(androidUser.getPassword().equals(oldpass))
        {
            userfind.setPassword(passwordEncoder.encode(newpass));
            androidUser.setPassword(newpass);
            userRepository.save(userfind);
            androidUserRepository.save(androidUser);

            //userRepository.delete(user1);
            return new ResponseEntity<>(new ResponseMessage("Password Changed"),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResponseMessage("Old password is wrong"),HttpStatus.BAD_REQUEST);
        }

       }else
           return new ResponseEntity<>(new ResponseMessage("No user in thatname"),HttpStatus.OK);
    }

    @RequestMapping("/findone")
    public TeacherInfo findone(String teacherid)
    {
        return teacherInfoRepository.findByTeacherid(teacherid);

    }



    @RequestMapping("/findinfo")
    public TeacherInfo findinfo(@RequestParam String teacherusername)
    {
        TeacherInfo teacherInfo1 = teacherInfoRepository.findTeacherInfoByTeacherusername(teacherusername);
        return teacherInfo1;
    }

}
