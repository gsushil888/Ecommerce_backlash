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
import java.util.List;
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

        VerificationToken verificationToken = createVerificationToken(localUser);
        emailService.sendVerificationEmail(verificationToken);

        return localUserDao.save(localUser);
    }

    private VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setLocalUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {

        Optional<LocalUser> opUser = localUserDao.findByUserNameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                if (user.getIsEmailVerified()) {
                    return jwtService.generateJWT(user);
                } else {
                    List<VerificationToken> verificationTokens = user.getVerificationTokens();
                    boolean resend = verificationTokens.size() == 0
                            || verificationTokens.get(0).getCreatedTimeStamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    if (resend) {
                        VerificationToken verificationToken = createVerificationToken(user);
                        verificationTokenDao.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }
                    throw new UserNotVerifiedException(resend);
                }

            }

        }
        return null;
    }

    public boolean verifyUser(String token) {

        Optional<VerificationToken> optionalVerificationToken = verificationTokenDao.findByToken(token);
        if (optionalVerificationToken.isPresent()) {
            VerificationToken verificationToken = optionalVerificationToken.get();
            LocalUser user = verificationToken.getLocalUser();
            if (!user.getIsEmailVerified()) {
                user.setIsEmailVerified(true);
                localUserDao.save(user);
                verificationTokenDao.deleteByLocalUser(user);
                return true;

            }
        }
        return false;
    }

}
