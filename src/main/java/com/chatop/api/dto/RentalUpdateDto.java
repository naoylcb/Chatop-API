package com.chatop.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalUpdateDto {

    @NotEmpty
    private String name;

    @NotNull
    private Float surface;

    @NotNull
    private Float price;

    @NotEmpty
    @Length(max = 2000)
    private String description;
}
