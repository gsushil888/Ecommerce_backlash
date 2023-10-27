package com.backlashprogramming.ecommerce.EcommerceByBacklash.exception;

public class UserAlreadyExistsException  extends  Exception{

    public  UserAlreadyExistsException(){
        super("User already exists,cannot register multiple account with same email");
    }
}
