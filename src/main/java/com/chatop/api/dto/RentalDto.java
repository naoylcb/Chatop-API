package com.chatop.api.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDto {

    @NotNull
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Float surface;

    @NotNull
    private Float price;

    @NotEmpty
    private String picture;

    @NotEmpty
    @Length(max = 2000)
    private String description;

    @NotNull
    @JsonProperty("owner_id")
    private Integer ownerId;

    @NotNull
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @NotNull
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}
