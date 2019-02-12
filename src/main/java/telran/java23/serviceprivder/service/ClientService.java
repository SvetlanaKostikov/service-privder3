package telran.java23.serviceprivder.service;

import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.RecordDto;
import telran.java23.serviceprivder.model.Client;
import telran.java23.serviceprivder.model.Record;

import java.util.Set;

public interface ClientService {
    public ClientDto addNewClient(ClientRegisterDto clientDto);
    public Client showProfileClient(String email);
    public ClientDto updateProfileClient(String email,ClientDto clientDto);
    public String deleteClient(String email);
    public boolean login(String auth);
  //  public Set<ProviderDto> showAllProvidersForClient();
 //   public Set<Record> showAllRecords();
    public Record createRecord(String email, String emailProvider, RecordDto recordDto);
//    public Record updateRecord();
//    public String deleteRecord();
//    public Integer vote();
//    public String comment();

}
