package com.menstalk.userservice.authentication.handler;

import org.hibernate.service.spi.ServiceException;

public class UsernameDuplicateException extends ServiceException {
    public UsernameDuplicateException(String message) {
        super(message);
    }
}
