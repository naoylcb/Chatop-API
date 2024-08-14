package com.chatop.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.chatop.api.dto.MessageDto;
import com.chatop.api.responses.BadRequestResponse;
import com.chatop.api.responses.MessageResponse;
import com.chatop.api.service.MessageService;

@RequestMapping("/api")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Operation(description = "Create a new message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)) })
    })
    @PostMapping("/messages")
    public ResponseEntity<MessageResponse> messages(@Valid @RequestBody MessageDto messageDto) {
        MessageResponse response = messageService.createMessage(messageDto);
        return ResponseEntity.ok(response);
    }

}
