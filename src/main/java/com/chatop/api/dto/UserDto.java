package com.chatop.api.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotNull
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @NotNull
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}
