package com.example.spring_boot_react_crud.controller;

import com.example.spring_boot_react_crud.model.Client;
import com.example.spring_boot_react_crud.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id) {
        if (id == null) return ResponseEntity.badRequest().build();

        Client client = this.service.getById(id);

        if (client == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {

        List<Client> clients = this.service.getAll();

        if (clients.isEmpty()) return ResponseEntity.notFound().build();


        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {

        if (client == null) return ResponseEntity.badRequest().build();

        this.service.save(client);

        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {

        if (id == null || client == null) return ResponseEntity.badRequest().build();

        this.service.update(id, client);

        return ResponseEntity.accepted().body(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {

        if (id == null) return ResponseEntity.badRequest().build();


        Client client = this.service.getById(id);

        if (client == null) return ResponseEntity.notFound().build();

        this.service.delete(id);

        return ResponseEntity.accepted().build();
    }
}
