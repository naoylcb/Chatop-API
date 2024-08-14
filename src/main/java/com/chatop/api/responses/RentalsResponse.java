package com.chatop.api.responses;

import java.util.ArrayList;

import com.chatop.api.dto.RentalDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalsResponse {

    private ArrayList<RentalDto> rentals;
}
