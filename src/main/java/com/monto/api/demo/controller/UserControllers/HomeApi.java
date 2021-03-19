package com.monto.api.demo.controller.UserControllers;


import com.monto.api.demo.model.userModel.ConfirmationToken;
import com.monto.api.demo.model.userModel.User;
import com.monto.api.demo.repository.UserRepositories.ConfirmationTokenRepository;
import com.monto.api.demo.repository.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@CrossOrigin("*")
public class HomeApi {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;



    @GetMapping("success")
    public String success(){
        return "index";
    }

    @GetMapping("pasresetform")
    public String reseform(){
        return "form";
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@Valid @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);


        }
        return "index";
    }
	

//	@PostMapping("/resetsend")
//    public String reset(@ModelAttribute(name="Passreset")@Valid Passreset passreset, Model model) {
//
//        User newpass= userRepository.findByEmailIgnoreCase(passreset.getEmail());
//
//        System.out.print(encoder.encode(passreset.getPassword()));
//
//        newpass.setPassword(encoder.encode(passreset.getPassword()));
//
//        userRepository.save(newpass);
//        return "success";
//    }

}

