package com.chatop.api.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.chatop.api.dto.LoginDto;
import com.chatop.api.dto.RegisterDto;
import com.chatop.api.responses.BadRequestResponse;
import com.chatop.api.responses.TokenResponse;
import com.chatop.api.service.AppUserService;

@RequestMapping("/api/auth")
@RestController
public class LoginController {

    @Autowired
    private AppUserService appUserService;

    @Operation(description = "Create a new user and return a jwt token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)) })
    })
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        TokenResponse response = appUserService.createUser(registerDto);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Check if user credentials is correct and return a jwt token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)) })
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginDto loginDto) {
        TokenResponse response = appUserService.connectUser(loginDto);
        return ResponseEntity.ok(response);

    }
}