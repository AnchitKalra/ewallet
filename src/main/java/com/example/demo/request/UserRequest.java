package com.example.demo.request;


import com.example.demo.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {



    @NotBlank(message = "Username cannot be blank")
    private String username;


    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Number cannot be blank")
    private String number;


    public User getUser() {
       return User.builder().emailId(this.getEmailId()).number(this.getNumber()).password(this.getPassword()).username(this.getUsername()).build();
    }


}
