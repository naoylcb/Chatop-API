package com.chatop.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.responses.RentalsResponse;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<Rental> getRentalById(Integer id) {
        return rentalRepository.findById(id);
    }

    public RentalDto getRentalInfoById(Integer id) {
        Rental rental = this.getRentalById(id).orElseThrow();
        RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
        return rentalDto;
    }

    public RentalsResponse getRentals() {
        Iterable<Rental> rentals = rentalRepository.findAll();

        ArrayList<RentalDto> transformedRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
            transformedRentals.add(rentalDto);
        }

        return new RentalsResponse(transformedRentals);
    }
}
