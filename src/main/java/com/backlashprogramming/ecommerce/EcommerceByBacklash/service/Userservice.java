package com.backlashprogramming.ecommerce.EcommerceByBacklash.service;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.RegistrationBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import org.springframework.stereotype.Service;

@Service
public class Userservice {

    public LocalUser registerUser(RegistrationBody registrationBody){
        LocalUser user=new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(registrationBody.getPassword());
        return  user;
    }

}
