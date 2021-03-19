package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import com.sun.org.apache.xpath.internal.operations.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/profileimage")
@CrossOrigin("*")
public class ProfileImageController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;


    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam String name) throws IOException {

        Optional<User> user = userRepository.findByUsername(name);
        if(!user.isPresent())
        {
            return new ResponseEntity<>(new ResponseMessage("No Accoutn in this username"), HttpStatus.BAD_REQUEST);
        }
        else {

            File convertFile = new File("C:\\Imagehost\\src\\assets\\user\\"+name+".jpg");
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();


            User user1 =user.get();
            user1.setImageUrl("http://localhost:4300/assets/user/"+name+".jpg");
            userRepository.save(user1);

            AndroidUser androidUser = androidUserRepository.findByUsername(name);
            androidUser.setWebprofileurl("http://localhost:4300/assets/user/"+name+".jpg");
            androidUser.setAndroidurl("http://10.0.2.2:4300/assets/user/"+name+".jpg");
            androidUserRepository.save(androidUser);

          return new ResponseEntity<>(new ResponseMessage("Image added"), HttpStatus.OK);
        }
    }

//    @GetMapping("userimage")
//    ResponseEntity<?> findimage(@RequestParam String usernamea)
//    {
//        Andr
//    }




}
