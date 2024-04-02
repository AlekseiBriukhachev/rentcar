package com.aleksei.rentcarapi.service;

import com.aleksei.rentcarapi.entity.Client;
import com.aleksei.rentcarapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClientsReturnsListOfClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(), new Client()));
        List<Client> clients = clientService.getAllClients();
        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientReturnsClientWhenExists() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        Client result = clientService.getClient(1L);
        assertEquals(client, result);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void getClientReturnsNullWhenDoesNotExist() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        Client result = clientService.getClient(1L);
        assertNull(result);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void createSavesClient() {
        Client client = new Client();
        clientService.create(client);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void deleteClientDeletesClient() {
        clientService.deleteClient(1L);
        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateThrowsExceptionWhenClientDoesNotExist() {
        Client client = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> clientService.update(1L, client));
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void updateUpdatesClientWhenExists() {
        Client existingClient = new Client();
        Client updatedClient = new Client();
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        clientService.update(1L, updatedClient);
        verify(clientRepository, times(1)).save(existingClient);
        verify(clientRepository, times(1)).findById(1L);
    }

}