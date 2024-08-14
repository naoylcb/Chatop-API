package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.responses.BadRequestResponse;
import com.chatop.api.responses.MessageResponse;
import com.chatop.api.responses.RentalsResponse;
import com.chatop.api.service.RentalService;

@RequestMapping("/api/rentals")
@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Operation(description = "Get all rentals.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RentalsResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<RentalsResponse> getRentals() {
        RentalsResponse response = rentalService.getRentals();
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Get a rental by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Integer id) {
        RentalDto rentalDto = rentalService.getRentalInfoById(id);
        return ResponseEntity.ok(rentalDto);
    }

    @Operation(description = "Create a new rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)) })
    })
    @PostMapping("")
    public ResponseEntity<MessageResponse> addRental(@Valid @ModelAttribute RentalCreateDto rentalCreateDto,
            Authentication authentication) {
        MessageResponse response = rentalService.createRental(rentalCreateDto, authentication);
        return ResponseEntity.ok(response);
    }
}
