package com.example.spring_boot_react_crud.service;

import com.example.spring_boot_react_crud.model.Client;
import com.example.spring_boot_react_crud.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void save(Client client) {
        repository.save(client);
        log.info("In ClientService save {}", client);
    }

    public Client getById(Long id) {
        Client client = repository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Client not found by this id :: " + id)
                );
        log.info("In ClientService getById - client: {} found by id: {}", client, id);

        return client;
    }

    public Client getByName(String name) {
        Client client = repository
                .findClientByFirstName(name)
                .orElseThrow(
                        () -> new NoSuchElementException("Client not found by this name :: " + name)
                );
        log.info("In ClientService getByName - client: {} found by name: {}", client, name);

        return client;
    }

    public void delete(Long id) {
        Client client = getById(id);
        repository.delete(client);

        log.info("In ClientService delete {}", client);
    }

    public void update(Long id, Client updateClient) {
        Client client = getById(id);

        if (updateClient.getFirstName() != null) client.setFirstName(updateClient.getFirstName());

        if (updateClient.getLastName() != null) client.setLastName(updateClient.getLastName());

        if (updateClient.getEmail() != null) client.setEmail(updateClient.getEmail());

        repository.save(client);
        log.info("In ClientService update {}", client);
    }

    public List<Client> getAll() {
        log.info("In ClientService getAll");

        return repository.findAll();
    }
}
