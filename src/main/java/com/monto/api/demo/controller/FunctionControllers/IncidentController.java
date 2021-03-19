package com.monto.api.demo.controller.FunctionControllers;


import com.monto.api.demo.message.response.ResponseMessage;
import com.monto.api.demo.model.Function.Incident;
import com.monto.api.demo.model.Function.Notification;
import com.monto.api.demo.model.userModel.AndroidUser;
import com.monto.api.demo.repository.FunctionsRepository.IncidentRepository;
import com.monto.api.demo.repository.FunctionsRepository.NotificationRepository;
import com.monto.api.demo.repository.UserRepositories.AndroidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("incident")
@CrossOrigin("*")
public class IncidentController {

    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    AndroidUserRepository androidUserRepository;

    @PostMapping("add_incident")
    public ResponseEntity<?> addincident (@RequestBody Incident incident)
    {
        Incident incident1 = new Incident(incident.getSender(),incident.getStudentnamee(),incident.getIncident(),incident.getTime(),incident.getLocation(),incident.getDate());
        incidentRepository.save(incident1);

        AndroidUser androidUser = androidUserRepository.findByUsername(incident.getSender());
        String weburl = androidUser.getWebprofileurl();
        String andrurl = androidUser.getAndroidurl();

        Notification notification = new Notification(incident.getSender(),incident.getIncident(),incident.getStudentnamee(),incident.getDate(),incident.getTime(),weburl,andrurl);
       notificationRepository.save(notification);
        return new ResponseEntity<>(new ResponseMessage("Incident added"), HttpStatus.OK);

    }




}
