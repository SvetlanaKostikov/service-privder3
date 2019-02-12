package telran.java23.serviceprivder.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java23.serviceprivder.dto.ClientDto;
import telran.java23.serviceprivder.dto.ProviderDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Document(collection = "Records")
public class Record {
    @Id
    String id;
    LocalDateTime startService;
    LocalDateTime endService;
    Service service;
    String	comment;
    ClientDto client;
    ProviderDto provider;


 public Record(LocalDateTime startService, LocalDateTime endService, Service service, String comment, ClientDto client, ProviderDto provider) {
  this.startService = startService;
  this.endService = endService;
  this.service = service;
  this.comment = comment;
  this.client = client;
  this.provider = provider;
 }
}
