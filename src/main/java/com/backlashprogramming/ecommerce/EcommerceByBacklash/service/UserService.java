package com.backlashprogramming.ecommerce.EcommerceByBacklash.service;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.LoginBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.RegistrationBody;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.dao.LocalUserDao;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.dao.VerificationTokenDao;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.VerificationToken;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.exception.EmailFailureException;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.exception.UserAlreadyExistsException;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.exception.UserNotVerifiedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private LocalUserDao localUserDao;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    EmailService emailService;

    @Autowired
    VerificationTokenDao verificationTokenDao;


    public LocalUser registerUser(@Valid RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {

        if (localUserDao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
                localUserDao.findByUserNameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        LocalUser localUser = new LocalUser();

        localUser.setEmail(registrationBody.getEmail());
        localUser.setFirstName(registrationBody.getFirstName());
        localUser.setLastName(registrationBody.getLastName());
        localUser.setUserName(registrationBody.getUsername());
        localUser.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        localUser = localUserDao.save(localUser);

        VerificationToken verificationToken=createVerificationToken(localUser);
        emailService.sendVerificationEmail(verificationToken);
        return localUserDao.save(localUser);
    }

    private VerificationToken createVerificationToken(LocalUser user){
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setLocalUser(user);
        user.getVerificationTokens().add(verificationToken);
        return  verificationToken;
    }

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException {

        Optional<LocalUser> opUser = localUserDao.findByUserNameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent())
        {
            LocalUser user=opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword()))
            {
                if (user.getIsEmailVerified()){
                    return  jwtService.generateJWT(user);
                }
                else {
                    throw  new UserNotVerifiedException(user.getIsEmailVerified());
                }

            }

        }
        return null;

    }


}
