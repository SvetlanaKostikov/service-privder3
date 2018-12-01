package telran.java23.serviceprivder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java23.serviceprivder.configuration.AccountConfiguration;
import telran.java23.serviceprivder.dao.ClientRepository;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ClientRegisterDto;
import telran.java23.serviceprivder.exeptions.UserExistEcxeption;
import telran.java23.serviceprivder.model.Client;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AccountConfiguration accountConfiguration;
    @Override
    public ClientDto addNewClient(ClientRegisterDto clientDto) {
        if (clientRepository.existsById(clientDto.getEmail())) {
            throw new UserExistEcxeption("Code: 409. User already exist!");

        }
        String hashPassword = encoder.encode(clientDto.getPassword());
        Client client = new Client(clientDto.getEmail(),hashPassword,clientDto.getFirstName(),clientDto.getLastName(),
                clientDto.getTelephone(),null);
        clientRepository.save(client);
        ClientDto clientIs = new ClientDto(clientDto.getFirstName(),clientDto.getLastName(),clientDto.getTelephone(),null);
        return clientIs;
    }

    @Override
    public ClientDto updateProfileClient(ClientDto clientDto) {

        return null;
    }
}
