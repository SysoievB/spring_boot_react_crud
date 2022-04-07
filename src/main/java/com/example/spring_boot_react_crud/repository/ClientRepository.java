package com.example.spring_boot_react_crud.repository;

import com.example.spring_boot_react_crud.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByFirstName(String firstName);
}
