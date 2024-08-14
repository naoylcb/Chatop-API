package com.chatop.api.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalCreateDto {

    @JsonIgnore
    private String pictureUrl;

    @NotEmpty
    private String name;

    @NotNull
    private Float surface;

    @NotNull
    private Float price;

    @NotNull
    private MultipartFile picture;

    @NotEmpty
    @Length(max = 2000)
    private String description;
}
