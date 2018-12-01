package telran.java23.serviceprivder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java23.serviceprivder.model.Client;

public interface ClientRepository extends MongoRepository<Client,String> {
}
