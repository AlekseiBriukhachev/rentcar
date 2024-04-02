package com.aleksei.rentcarapi.controllers;

import com.aleksei.rentcarapi.entity.Client;
import com.aleksei.rentcarapi.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClientReturnsClientWhenExists() {
        Client client = new Client();
        when(clientService.getClient(1L)).thenReturn(client);
        ResponseEntity<Client> result = clientController.getClient(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(client, result.getBody());
    }

    @Test
    void getClientReturnsNotFoundWhenDoesNotExist() {
        when(clientService.getClient(1L)).thenReturn(null);
        ResponseEntity<Client> result = clientController.getClient(1L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getAllClientsReturnsListOfClients() {
        when(clientService.getAllClients()).thenReturn(Arrays.asList(new Client(), new Client()));
        ResponseEntity<List<Client>> result = clientController.getAllClients();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody() != null ? result.getBody().size() : 0);
    }

    @Test
    void addClientReturnsCreatedStatus() {
        Client client = new Client();
        ResponseEntity<String> result = clientController.addClient(client);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void deleteClientReturnsOkStatus() {
        ResponseEntity<String> result = clientController.deleteClient(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}