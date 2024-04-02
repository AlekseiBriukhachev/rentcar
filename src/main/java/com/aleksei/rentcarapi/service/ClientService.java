package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.Client;
import com.aleksei.rentcarapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClientService is a service class that provides methods for managing clients in the system.
 * It uses ClientRepository to perform database operations.
 */
@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * Constructs a new ClientService with the given ClientRepository.
     *
     * @param clientRepository the ClientRepository to use for database operations
     */
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Retrieves a client by its ID.
     *
     * @param id the ID of the client to retrieve
     * @return the client with the given ID, or null if no such client exists
     */
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new client in the database.
     *
     * @param client the client to create
     */
    @Transactional
    public void create(Client client) {
        clientRepository.save(client);
    }

    /**
     * Deletes a client by its ID.
     *
     * @param id the ID of the client to delete
     */
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    /**
     * Updates a client in the database.
     *
     * @param id the ID of the client to update
     * @param updatedClient the client with updated information
     * @throws EntityNotFoundException if no client with the given ID exists
     */
    @Transactional
    public void update(Long id, Client updatedClient) {
        var existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));
        existingClient.setName(updatedClient.getName());
        existingClient.setSurname(updatedClient.getSurname());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setCity(updatedClient.getCity());
        existingClient.setZipCode(updatedClient.getZipCode());
        existingClient.setCountry(updatedClient.getCountry());

        clientRepository.save(existingClient);
    }
}
