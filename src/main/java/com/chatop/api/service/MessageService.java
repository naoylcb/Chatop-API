package com.chatop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.MessageDto;
import com.chatop.api.model.AppUser;
import com.chatop.api.model.Message;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.responses.MessageResponse;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RentalService rentalService;

    public MessageResponse createMessage(MessageDto messageDto) {
        AppUser appUser = appUserService.getUserById(messageDto.getUserId()).orElseThrow();
        Rental rental = rentalService.getRentalById(messageDto.getRentalId()).orElseThrow();

        Message newMessage = new Message();
        newMessage.setMessage(messageDto.getMessage());
        newMessage.setUser(appUser);
        newMessage.setRental(rental);
        messageRepository.save(newMessage);

        return new MessageResponse("Message send with success");
    }

}
