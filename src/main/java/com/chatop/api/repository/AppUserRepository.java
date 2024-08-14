package com.chatop.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    public Optional<AppUser> findByEmail(String email);
}
