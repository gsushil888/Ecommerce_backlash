//package com.backlashprogramming.ecommerce.EcommerceByBacklash.service;
//
//import com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model.RegistrationBody;
//import com.backlashprogramming.ecommerce.EcommerceByBacklash.exception.UserAlreadyExistsException;
//import com.icegreen.greenmail.configuration.GreenMailConfiguration;
//import com.icegreen.greenmail.junit5.GreenMailExtension;
//import com.icegreen.greenmail.util.ServerSetupTest;
//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.RegisterExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Slf4j
//public class UserServiceTest {
//
//    @RegisterExtension
//    private  static GreenMailExtension greenMailExtension=new GreenMailExtension(ServerSetupTest.SMTP)
//            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot","secret"))
//            .withPerMethodLifecycle(true);
//
//    @Autowired
//    private  UserService userService;
//
//   @Test
//   @Transactional
//   public  void testRegisterUser() throws MessagingException {
//       RegistrationBody registrationBody=new RegistrationBody();
//       registrationBody.setUsername("UserA");
//       registrationBody.setEmail("UserServiceTest$testRegisterUser@junit.com");
//       registrationBody.setFirstName("FirstName");
//       registrationBody.setLastName("LastName");
//       registrationBody.setPassword("MySecretPassword123");
//       log.info("user A created in testing....");
//
//       log.info("Registering using testing using assertions");
//
//       Assertions.assertThrows(UserAlreadyExistsException.class,
//               ()-> userService.registerUser(registrationBody),"Username should already be in use.");
//
//
//       registrationBody.setUsername("UserServiceTest$testRegisterUser");
//       registrationBody.setEmail("UserA@junit.com");
//
//
//
//       Assertions.assertThrows(UserAlreadyExistsException.class,
//               ()-> userService.registerUser(registrationBody),"Username already be in use.");
//
//       registrationBody.setEmail("UserServiceTest$testRegisterUser@junit.com");
//
//       Assertions.assertDoesNotThrow(()->userService.registerUser(registrationBody)
//               ,"User should register successfully.");
//
//       Assertions.assertEquals(registrationBody.getEmail()
//               ,greenMailExtension.getReceivedMessages()[0].getRecipients(Message.RecipientType.TO)[0].toString());
//
//       log.info("test Ok...");
//
//   }
//
//}
