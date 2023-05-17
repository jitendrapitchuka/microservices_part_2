package com.jitendra.user.service.UserService.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found excep");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
