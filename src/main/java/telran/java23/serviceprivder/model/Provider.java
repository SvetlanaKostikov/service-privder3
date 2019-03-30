package telran.java23.serviceprivder.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java23.serviceprivder.dto.RecordUpdateDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="email")
@Document(collection = "Providers")
public class Provider {
    @Id
    String email;
    String password;
    String profession;
    String firstName;
    String lastName;
    Long telephone;
    Long whatsApp;
    Address address;
    Boolean isActive;
    Set<Service> services;
    //String - eto LocalDateTime
    LinkedHashMap<String, Record> records;

    Schedule schedule;
    //String - eto LocalDate
    LinkedHashMap<String, DayOfWeekReal> realSchedule;
    ArrayList<Integer> vote;
    Double averageVote;


    public LinkedHashMap<String, DayOfWeekReal> twoWeeksSchedule() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDate dateNow = LocalDate.now();
        realSchedule = new LinkedHashMap<>();
        for (int i = 0; i < 13; i++) {
            LocalDate date = dateNow.plusDays(i);
            realSchedule.put(date.toString(), new DayOfWeekReal(schedule.findDay(date.getDayOfWeek().name())));

        }
        for (String key : records.keySet()) {
            LocalDateTime keyParse = LocalDateTime.parse(key, formatter);
            if (keyParse.isAfter(LocalDateTime.now())) {
                DayOfWeekReal dayOfWeekReal = realSchedule.get(keyParse.toLocalDate().toString());
                LinkedHashMap<String, Boolean> timeRecords = dayOfWeekReal.getRealTimeRecords();
                timeRecords.put(keyParse.toLocalTime().toString(), false);
                dayOfWeekReal.setRealTimeRecords(timeRecords);
                realSchedule.put(keyParse.toString(), dayOfWeekReal);
            }
            else{
                records.remove(key);
            }
        }
        return realSchedule;
    }


    public Record findRecord(String id){
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            if(entry.getValue().getId().equals(id)){
                return entry.getValue();
            }
        }
        return null;
    }
    public String findKeyRecord(String id){
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            if(entry.getValue().getId().equals(id)){
                return entry.getKey();
            }
        }
        return null;
    }

    public void deleteRecord(String id){
        records.remove(findKeyRecord(id));
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfession() {
        return profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getTelephone() {
        return telephone;
    }

    public Long getWhatsApp() {
        return whatsApp;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Set<Service> getServices() {
        return services;
    }

    public LinkedHashMap<String, Record> getRecords() {
        return records;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public LinkedHashMap<String, DayOfWeekReal> getRealSchedule() {
        return twoWeeksSchedule();
    }

    public ArrayList<Integer> getVote() {
        return vote;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Double getAverageVote() {
        return averageVote;
    }
}






