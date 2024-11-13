package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.resource.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    RestTemplate restTemplate;
    @Override
    public void saveUser(UserRequest userRequest) {
        User user = userRequest.getUser();
    User user1 = userRepository.findUserByUsername(user.getUsername());
       if(Objects.nonNull(user1)) {
           System.out.println("user1");
           return;
       }
       user.setPassword(encoder.encode(user.getPassword()));
      user =  userRepository.save(user);
      kafkaTemplate.send("user_created", String.valueOf(user.getId()));
    }


    @Override
    public UserResponse getUser(UserRequest userRequest) {
        User user = userRequest.getUser();
        User user1 = userRepository.findUserByUsername(user.getUsername());
        if(Objects.nonNull(user1)) {
             if(user.getPassword().equals(user1.getPassword())) {
                 UserResponse userResponse = new UserResponse();
                 return  userResponse.getUserResponse(user1);
           }
       }
        return null;

    }

    @Override
    public Optional<User> getUserByNumber(String number) {
        return userRepository.findUserByNumber(number);
    }


    @Override
    public Boolean performTransaction(TransactionRequest transactionRequest) {
        Optional<User> user = getUserByNumber(transactionRequest.getSenderNumber());
        if(user.isPresent()) {
            transactionRequest.setSenderId(user.get().getId());
            transactionRequest.setSenderEmail(user.get().getEmailId());
            user = getUserByNumber(transactionRequest.getReceiverNumber());
            if(user.isPresent()) {
                transactionRequest.setReceiverId(user.get().getId());
                System.out.println(user.get().getEmailId());
                transactionRequest.setReceiverEmail(user.get().getEmailId());
            }
        }


        String url = "http://localhost:8082/transaction/wallet";
        Boolean success =restTemplate.postForEntity("http://localhost:8082/transaction/wallet", transactionRequest, Boolean.class).getBody();
        return success;
    }


}
