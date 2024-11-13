package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.exception.UserException;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.resource.TransactionRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    UserService userService;





    @PostMapping("user")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserRequest userRequest) throws UserException{
       userService.saveUser(userRequest);
       return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("user/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserRequest userRequest) throws UserException {
        UserResponse user = userService.getUser(userRequest);
        if(Objects.nonNull(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        throw new UserException("User Not Valid");
    }

    @PostMapping("/user/transaction")

    public ResponseEntity<Boolean> performTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {

        System.out.println(transactionRequest.getAmount() + " " + transactionRequest.getSenderId() + " " + transactionRequest.getSenderNumber());
        return new ResponseEntity<>(userService.performTransaction(transactionRequest), HttpStatus.OK);


    }



}
