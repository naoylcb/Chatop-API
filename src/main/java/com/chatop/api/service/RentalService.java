package com.chatop.api.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.dto.RentalCreateDto;
import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalUpdateDto;
import com.chatop.api.model.AppUser;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.responses.MessageResponse;
import com.chatop.api.responses.RentalsResponse;

@Service
public class RentalService {

    @Value("${chatop.images.folder}")
    private String imagesFolder;

    @Value("${chatop.images.base.url}")
    private String imagesBaseUrl;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppUserService appUserService;

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

    public MessageResponse createRental(RentalCreateDto rentalCreateDto, Authentication authentication) {
        AppUser appUser = appUserService.getUserByEmail(authentication.getName()).orElseThrow();

        rentalCreateDto.setPictureUrl(this.uploadFile(rentalCreateDto.getPicture()));

        Rental rental = modelMapper.map(rentalCreateDto, Rental.class);
        rental.setOwner(appUser);
        rentalRepository.save(rental);

        return new MessageResponse("Rental created !");
    }

    public MessageResponse updateRental(Integer id, RentalUpdateDto rentalUpdateDto) {
        Rental rental = this.getRentalById(id).orElseThrow();
        modelMapper.map(rentalUpdateDto, rental);
        rentalRepository.save(rental);

        return new MessageResponse("Rental updated !");
    }

    private String uploadFile(MultipartFile file) {
        try {
            int extensionIndex = file.getOriginalFilename().lastIndexOf(".");
            String fileExtension = file.getOriginalFilename().substring(extensionIndex); // get only extension from original file name
            String newFileName = UUID.randomUUID().toString() + fileExtension; // Rename file with uuid to prevent if multiple users upload files with same name
            Path destination = Path.of(imagesFolder).resolve(newFileName).normalize()
                    .toAbsolutePath();
            file.transferTo(destination);
            return imagesBaseUrl + newFileName;
        } catch (IOException e) {
            System.out.println("Erreur lors de l'upload du fichier : " + e.getMessage());
            return "";
        }
    }
}
