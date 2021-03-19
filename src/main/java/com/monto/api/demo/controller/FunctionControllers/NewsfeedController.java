package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.NewsFeedUpload;
import com.monto.api.demo.repository.FunctionsRepository.NewsFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@RequestMapping("/newsfeed")
@RestController
@CrossOrigin("*")
public class NewsfeedController {

    @Autowired
    NewsFeedRepository newsFeedRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam String name , @RequestParam String description ) throws IOException {

        File convertFile = new File("C:\\Imagehost\\src\\assets\\imgs\\"+name+".jpg");
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        newsFeedRepository.save(new NewsFeedUpload(name,description,"http://localhost:4300/assets/imgs/"+name+".jpg","http://10.0.2.2:4300/assets/imgs/"+name+".jpg"));
        return new ResponseEntity<>(new ResponseMessage("File was uploadede succesfully"), HttpStatus.OK);
    }


    @RequestMapping("/findall")
    public List<NewsFeedUpload> findall()
    {
        List<NewsFeedUpload> newsFeedUploads = newsFeedRepository.findAll();
        return newsFeedUploads;
    }


}
