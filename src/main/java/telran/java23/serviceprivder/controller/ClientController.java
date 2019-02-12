package telran.java23.serviceprivder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.dto.RecordDto;
import telran.java23.serviceprivder.model.Client;
import telran.java23.serviceprivder.model.Record;
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
    @GetMapping("/profile/{email}")
    public Client showProfileClient(@PathVariable String email){
        return clientService.showProfileClient(email);
    }
    @PutMapping("/update/{email}")
    public ClientDto updateClient(@PathVariable String email, @RequestBody ClientDto clientDto){
        return clientService.updateProfileClient(email,clientDto);

    }
    @DeleteMapping("/{email}")
    public String deleteClient(@PathVariable String email){
        return clientService.deleteClient(email);
    }

    @GetMapping("/login")
    public boolean login(@RequestHeader(value = "Authorization")String auth){
        return clientService.login(auth);
    }

    @PostMapping("/newrecord/{email}/{emailProvider}")
    public Record createRecord(@PathVariable String email,@PathVariable String emailProvider,@RequestBody RecordDto recordDto){
        return clientService.createRecord(email,emailProvider,recordDto );
    }


}


