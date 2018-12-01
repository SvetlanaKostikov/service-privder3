package telran.java23.serviceprivder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import telran.java23.serviceprivder.model.Provider;
@Repository
public interface ProviderRepository extends MongoRepository<Provider,String> {

}
