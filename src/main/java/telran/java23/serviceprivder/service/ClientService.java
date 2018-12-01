package telran.java23.serviceprivder.service;

import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.model.Client;

public interface ClientService {
    public ClientDto addNewClient(ClientRegisterDto clientDto);
    public ClientDto updateProfileClient(ClientDto clientDto);
}
