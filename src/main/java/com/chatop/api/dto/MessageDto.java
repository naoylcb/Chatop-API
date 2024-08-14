package com.chatop.api.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDto {

    @NotNull
    @JsonProperty("rental_id")
    private Integer rentalId;

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    @NotEmpty
    @Length(max = 2000)
    private String message;

}
