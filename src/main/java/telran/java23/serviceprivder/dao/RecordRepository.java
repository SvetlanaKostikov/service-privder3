package telran.java23.serviceprivder.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import telran.java23.serviceprivder.model.Record;

@Repository
public interface RecordRepository extends MongoRepository<Record,String> {

}
