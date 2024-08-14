package com.chatop.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

}
