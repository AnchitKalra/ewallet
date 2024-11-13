package com.example.demo.service.resource;


import com.example.demo.domain.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest  {


    private String senderNumber;

    private String receiverNumber;


    private Double amount;


    @Enumerated(value = EnumType.STRING)
    @JsonProperty
    private TransactionType type;

    private Long senderId;

    private Long receiverId;


    private String senderEmail;

    private String receiverEmail;



}
