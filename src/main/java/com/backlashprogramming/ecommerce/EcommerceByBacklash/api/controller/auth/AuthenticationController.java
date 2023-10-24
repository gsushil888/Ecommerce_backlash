package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.controller.auth;


import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.RegistrationBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    public Userservice userservice;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationBody registrationBody){
        userservice.registerUser(registrationBody);
    }
}
