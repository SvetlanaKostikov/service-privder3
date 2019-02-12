package telran.java23.serviceprivder.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java23.serviceprivder.configuration.AccountConfiguration;
import telran.java23.serviceprivder.dao.ClientRepository;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dao.RecordRepository;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.RecordDto;
import telran.java23.serviceprivder.exeptions.ErrorTimeException;
import telran.java23.serviceprivder.exeptions.UserExistEcxeption;
import telran.java23.serviceprivder.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProviderServiceImpl providerService;
    @Autowired
    RecordRepository recordRepository;

//    @Autowired
//    PasswordEncoder encoder;

    @Autowired
    AccountConfiguration accountConfiguration;
    @Override
    public ClientDto addNewClient(ClientRegisterDto clientDto) {
        if (clientRepository.existsById(clientDto.getEmail())) {
            throw new UserExistEcxeption("User already exist!");

        }
//        String hashPassword = encoder.encode(clientDto.getPassword());
        Client client = new Client(clientDto.getEmail(),clientDto.getPassword(),clientDto.getFirstName(),clientDto.getLastName(),
                clientDto.getTelephone(),null);
        clientRepository.save(client);
        ClientDto clientIs = new ClientDto(clientDto.getFirstName(),clientDto.getLastName(),clientDto.getTelephone());
        return clientIs;
    }
    @Override
    public Client showProfileClient(String email){
        clientRepository.findById(email).get();
        return clientRepository.findById(email).get();
    }

    @Override
    public ClientDto updateProfileClient(String email, ClientDto clientDto) {
        Client client = clientRepository.findById(email).get();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setTelephone(clientDto.getTelephone());
        clientRepository.save(client);
        return clientDto;
    }
    public String deleteClient(String email){
        clientRepository.deleteById(email);
        return "Client is deleted";
    }
    @Override
    public boolean login(String auth) {//TODO - razobratsya s exeptions
        AccountUserCredential credentials = accountConfiguration.tokens(auth);
        Client client = clientRepository.findById(credentials.getLogin()).get();
        String hashPassword = credentials.getPassword();
        if (!hashPassword.equals(client.getPassword())) {
            return false;
        }

        return true;
    }
    @Override
    public Record createRecord(String email, String emailProvider, RecordDto recordDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(recordDto.getStartService(),formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(recordDto.getEndService(),formatter);
        LocalTime time = dateTimeStart.toLocalTime();
        LocalDate date=dateTimeStart.toLocalDate();
        Provider provider = providerRepository.findById(emailProvider).orElse(null);
        if(provider==null){
            throw new UserExistEcxeption("Provider not exist");
        }

        ProviderDto providerDto = providerService.providerToProviderDto(provider);
        Client client = clientRepository.findById(email).orElse(null);
        if(client==null){
            throw new UserExistEcxeption("Client not exist");
        }
        ClientDto clientDto = clientToClientDto(client);
        Record record = new Record(dateTimeStart,dateTimeEnd,recordDto.getService(),recordDto.getComment(),
                clientDto, providerDto);

        LinkedHashMap<LocalDate,DayOfWeek> realSchedule= provider.getRealSchedule();
        DayOfWeek dayOfWeek=realSchedule.get(date);
        LinkedHashMap<String, Boolean> timeRecords=dayOfWeek.getTimeRecords();
        if(timeRecords.get(time.toString())==true){
            LinkedHashMap<LocalDateTime,Record>records=provider.getRecords();
            records.put(dateTimeStart,record);
            provider.setRecords(records);

            LinkedHashMap<LocalDateTime,Record>clientRecords=client.getRecords();
            clientRecords.put(dateTimeStart,record);
            client.setRecords(records);
            clientRepository.save(client);
            recordRepository.save(record);


            timeRecords.put(time.toString(),false);
            dayOfWeek.setTimeRecords(timeRecords);
            realSchedule.put(date,dayOfWeek);
            provider.setRealSchedule(realSchedule);
            providerRepository.save(provider);
        }else{
            throw new ErrorTimeException("This time is taken");
        }

        return record;

    }

    public ClientDto clientToClientDto(Client client){
        ClientDto clientDto = new ClientDto(client.getFirstName(),client.getLastName(),client.getTelephone());
        return clientDto;
    }
}
