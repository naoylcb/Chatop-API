package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.chatop.api.dto.UserDto;
import com.chatop.api.service.AppUserService;

@RequestMapping("/api")
@RestController
@RestControllerAdvice
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Operation(description = "Get an user by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> user(@PathVariable Integer id) {
        UserDto userDto = appUserService.getUserInfoById(id);
        return ResponseEntity.ok(userDto);
    }

}
