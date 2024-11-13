package com.example.demo.response;


import com.example.demo.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String number;

    private String emailId;

    public UserResponse getUserResponse(User user) {
        return UserResponse.builder().emailId(user.getEmailId()).id(user.getId()).number(user.getNumber()).username(user.getUsername()).build();
    }
}
