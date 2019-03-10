package telran.java23.serviceprivder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.dto.RecordDto;
import telran.java23.serviceprivder.dto.RecordUpdateDto;
import telran.java23.serviceprivder.model.Client;
import telran.java23.serviceprivder.model.Record;
import telran.java23.serviceprivder.service.ClientService;

import java.util.Set;

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
    @PutMapping("/vote/{email}/{emailProvider}/{vote}")
    public Double voteProvider(@PathVariable String email,@PathVariable String emailProvider, @PathVariable Integer vote){
        return clientService.voteProvider(email,emailProvider,vote);
    }
    @GetMapping("/showproviders/{email}")
    public Set<String> showAllProvidersForClient(@PathVariable String email){
        return clientService.showAllProvidersForClient(email);
    }
    @GetMapping("/showallrecords/{email}")
    public Set <Record> showAllrecords(@PathVariable String email){
        return clientService.showAllrecords(email);
    }
    @PutMapping("/updaterecord/{recordId}")
    public Record updateRecord (@PathVariable String recordId, @RequestBody RecordUpdateDto recordDto){
        return clientService.updateRecord(recordId,recordDto);
    }
    @DeleteMapping("/record/{recordId}")
    public Record deleteRecord (@PathVariable String recordId){
        return clientService.deleteRecord(recordId);
    }

    @GetMapping("/showrecords/{email}/{date}")
    public Set<Record>showAllrecordsForDay(@PathVariable String email,@PathVariable String date){
        return clientService.showAllrecordsForDay(email,date);
    }
    @GetMapping("/archiverecords/{email}/")
    public Set<Record> showArchiveRecords(@PathVariable String email){
        return clientService.showArchiveRecords(email);
    }

}


