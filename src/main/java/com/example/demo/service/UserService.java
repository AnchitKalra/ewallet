package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.resource.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface UserService {


    public void saveUser(UserRequest userRequest);

    public UserResponse getUser(UserRequest userRequest);

    public Optional<User> getUserByNumber(String number);


    public Boolean performTransaction(TransactionRequest transactionRequest);
}
