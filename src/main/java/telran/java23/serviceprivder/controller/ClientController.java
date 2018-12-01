package telran.java23.serviceprivder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.model.Client;
import telran.java23.serviceprivder.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ProviderRepository providerRepository;

    @PostMapping("/newclient")
    public ClientDto addNewClient(@RequestBody ClientRegisterDto clientDto){
        return clientService.addNewClient(clientDto);
    }
}
