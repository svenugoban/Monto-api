package com.monto.api.demo.controller.UserControllers;


import com.monto.api.demo.message.request.LoginForm;
import com.monto.api.demo.message.request.ProfileForm;
import com.monto.api.demo.message.request.SignUpForm;
import com.monto.api.demo.message.response.JwtResponse;
import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Badges;
import com.monto.api.demo.model.Function.NewsFeedUpload;
import com.monto.api.demo.model.userModel.*;
import com.monto.api.demo.repository.FunctionsRepository.BadgesRepository;
import com.monto.api.demo.repository.FunctionsRepository.NewsFeedRepository;
import com.monto.api.demo.repository.UserRepositories.*;
import com.monto.api.demo.security.jwt.JwtProvider;
import com.monto.api.demo.security.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Controller
public class AuthRestAPIs {


	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

//	@Autowired
//	private EmailSenderService emailSenderService;
	//_________________________________
// security
	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
// __________________________

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AndroidUserRepository androidUserRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	BadgesRepository badgesRepository;

	@Autowired
	NewsFeedRepository newsFeedRepository;

	@Autowired
	EmailSenderService emailSenderService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		              Authentication authentication = authenticationManager.authenticate(
			                                                    	new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
																			                                loginRequest.getPassword() )
					                                                                    );

		                    SecurityContextHolder.getContext().setAuthentication(authentication);

		                   String jwt = jwtProvider.generateJwtToken(authentication);
	                       UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	                       AndroidUser androidUser1 = androidUserRepository.findByUsername(loginRequest.getUsername());

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

		                  return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {


		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Username is already Exists"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Email is already in use"),
					HttpStatus.BAD_REQUEST);
		}

		User users = new User( signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		users.setEnabled(true);




		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();



		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);

				roles.add(adminRole);

				break;

				case "teacher":
					Role teacherRole = roleRepository.findByName(RoleName.ROLE_TEACHER);
					roles.add(teacherRole);
					break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_PARENT);
				roles.add(userRole);
			}
		});

		users.setRoles(roles);
            ConfirmationToken confirmationToken = new ConfirmationToken(users);
            confirmationTokenRepository.save(confirmationToken);
            userRepository.save(users);

            String rol = signUpRequest.getRole().toString();
            String roleadd;
            if(rol.equals("[parent]"))
				{
					roleadd = "parent";
				}
            else if(rol.equals("[teacher]"))
				{
					roleadd = "teacher";
				}
            else
				{
					roleadd = "admin";
				}


		AndroidUser androidUser = new AndroidUser(signUpRequest.getEmail(),signUpRequest.getPassword(),roleadd,signUpRequest.getUsername());
		androidUserRepository.save(androidUser);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(users.getEmail());
		mailMessage.setSubject("welcome to Complete Registration!");
		mailMessage.setFrom("thushapan1996@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+"http://localhost:8080/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

		emailSenderService.sendEmail(mailMessage);

		return new ResponseEntity<>(new ResponseMessage("User Created"), HttpStatus.OK);
	}



@RequestMapping("check")
public ResponseEntity<?> sendmail()
{
	//ConfirmationToken confirmationToken = new ConfirmationToken(user);

	//confirmationTokenRepository.save(confirmationToken);

	SimpleMailMessage mailMessage = new SimpleMailMessage();
	mailMessage.setTo("sindujan96@gmail.com");
	mailMessage.setSubject("welcome to Complete Registration!");
	mailMessage.setFrom("sindujan96@gmail.com");
//	mailMessage.setText("To confirm your account, please click here : "
//			+"http://localhost:8080/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

	mailMessage.setText("Checking jva mail ");
	emailSenderService.sendEmail(mailMessage);
	return new ResponseEntity<>(new ResponseMessage("User registered successfully! get mail"), HttpStatus.OK);
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
		return "mail registation sucessfull";
	}












//	@PostMapping("/profile")
//	public ResponseEntity<?> Userprofileupdate(@RequestBody ProfileForm update) {
//
//		User profile= userRepository.findByEmailIgnoreCase(update.getEmail());
//
//
//
//		profile.setName(update.getName());
//
////		profile.setEmail(update.getEmail());
//		profile.setPassword(encoder.encode(update.getPassword()));
//		profile.setAddress(update.getAddress());
//		profile.setDob(update.getDob());
//		profile.setGender(update.getGender());
//		profile.setIsnotification(update.isIsnotification());
//		userRepository.save(profile);
//		return new ResponseEntity<>(new ResponseMessage("details updated"), HttpStatus.OK);
//
//	}

//	@PostMapping("/passwordreset")
//	public ResponseEntity<?> resetpassword(@Valid @RequestBody ProfileForm reset) {
//
//
//		if (userRepository.existsByEmail(reset.getEmail())){
//
//			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			mailMessage.setTo(reset.getEmail());
//			mailMessage.setSubject("RESET THE PASSWORD!");
//			mailMessage.setFrom("sindujan96@gmail.com");
//			mailMessage.setText("To Reset your password : click the link "
//					+"http://localhost:4200/auth/passrese t");
//
//			emailSenderService.sendEmail(mailMessage);
//System.out.print("hi");
//		//	return new ResponseEntity<>(new ResponseMessage(" mail send"), HttpStatus.OK);
//		}
//		System.out.print("bye");
//			return new ResponseEntity<>(new ResponseMessage("reseting mail send"), HttpStatus.OK);
//	}



//	@PostMapping("/resetsend")
//	public ResponseEntity<?> reset(@Valid @RequestBody ProfileForm reset) {
//
//		User newpass= userRepository.findByEmailIgnoreCase(reset.getEmail());
//
//
//		newpass.setPassword(encoder.encode(reset.getPassword()));
//         userRepository.save(newpass);
//		return new ResponseEntity<>(new ResponseMessage("reseting mail "), HttpStatus.OK);
//	}
//
//	@GetMapping("/reset")
//	public String password(Map<String, Object> model) {
//		return "reset";
//	}











}
