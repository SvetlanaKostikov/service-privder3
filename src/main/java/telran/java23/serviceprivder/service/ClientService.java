package telran.java23.serviceprivder.service;

import telran.java23.serviceprivder.dto.*;
import telran.java23.serviceprivder.model.Client;
import telran.java23.serviceprivder.model.Record;

import java.util.Set;

public interface ClientService {
    public ClientDto addNewClient(ClientRegisterDto clientDto);
    public Client showProfileClient(String email);
    public ClientDto updateProfileClient(String email, ClientDto clientDto);
    public String deleteClient(String email);
    public boolean login(String auth);
    public Double voteProvider(String email, String emailProvider, Integer vote);
    public Record createRecord(String email, String emailProvider, RecordDto recordDto);
    public Set<String> showAllProvidersForClient(String email);
    public Set <Record> showAllrecords(String email);
    public Record updateRecord (String recordId, RecordUpdateDto recordDto);
    public Record deleteRecord (String recordId);
    public Set<Record>showAllrecordsForDay(String email,String date);
    public Set<Record> showArchiveRecords(String email);
}