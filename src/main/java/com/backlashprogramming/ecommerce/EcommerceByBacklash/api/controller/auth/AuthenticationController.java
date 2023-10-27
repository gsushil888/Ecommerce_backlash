package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.controller.auth;


import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.LoginBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.LoginResponse;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.RegistrationBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//            return  new ResponseEntity("Email Exists",HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {

        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }


    @GetMapping("/me")
    public LocalUser getLoggedInUerProfile(@AuthenticationPrincipal LocalUser user) {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }


}
