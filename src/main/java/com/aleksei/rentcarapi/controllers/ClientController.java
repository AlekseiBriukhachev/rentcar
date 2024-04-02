package com.aleksei.rentcarapi.controllers;

import com.aleksei.rentcarapi.entity.Client;
import com.aleksei.rentcarapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClientController is a REST controller that provides endpoints for managing clients in the system.
 * It uses ClientService to perform business logic operations.
 */
@RestController
@RequestMapping("/api/client")
@Tag(name = "Client Controller", description = "Client API")
public class ClientController {
    private final ClientService clientService;

    /**
     * Constructs a new ClientController with the given ClientService.
     *
     * @param clientService the ClientService to use for client-related operations
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Endpoint to get a client by its ID.
     *
     * @param id the ID of the client to retrieve
     * @return a ResponseEntity containing the client and an HTTP status code
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Get client by ID")
    public ResponseEntity<Client> getClient(@PathVariable @Parameter(description = "Client ID") Long id) {
        var client = clientService.getClient(id);
        return new ResponseEntity<>(client, client != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to get all clients.
     *
     * @return a ResponseEntity containing a list of all clients and an HTTP status code
     */
    @GetMapping("/all")
    @Operation(summary = "Get all clients", description = "Get all clients")
    public ResponseEntity<List<Client>> getAllClients() {
        var clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    /**
     * Endpoint to add a new client.
     *
     * @param client the client to add
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @PostMapping("/add")
    @Operation(summary = "Add client", description = "Add client")
    public ResponseEntity<String> addClient(@RequestBody @Parameter(description = "New client") Client client) {
        clientService.create(client);
        return new ResponseEntity<>("Client was added", HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete a client by its ID.
     *
     * @param id the ID of the client to delete
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete client", description = "Delete client")
    public ResponseEntity<String> deleteClient(@PathVariable @Parameter(description = "Client ID") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>("Client with ID = " + id + " was deleted", HttpStatus.OK);
    }

    /**
     * Endpoint to update a client.
     *
     * @param id the ID of the client to update
     * @param client the updated client
     * @return a ResponseEntity containing a success message and an HTTP status code
     */
    @PutMapping("/update")
    @Operation(summary = "Update client", description = "Update client")
    public ResponseEntity<String> updateClient(@RequestParam @Parameter(description = "Client ID") Long id,
                                               @RequestBody @Parameter(description = "Updated client") Client client) {
        try {
            clientService.update(id, client);
            return new ResponseEntity<>("Client was updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Client was not updated: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
