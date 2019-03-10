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
    LinkedHashMap<String, DayOfWeek> realSchedule;
    ArrayList<Integer> vote;
    Double averageVote;


    //    public LinkedHashMap<String, DayOfWeek> twoWeeksSchedule() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//        LocalDate dateNow = LocalDate.now();
//        realSchedule = new LinkedHashMap<>();
//        Locale locale = Locale.getDefault();
//        for (int i = 0; i < 13; i++) {
//            LocalDate date = dateNow.plusDays(i);
//            realSchedule.put(date.toString(), schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)));
//            for (String key : records.keySet()) {
//                LocalDateTime keyParse = LocalDateTime.parse(key, formatter);
//                if (date.equals(keyParse.toLocalDate())) {
//                    DayOfWeek dayOfWeek = schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale));
//                    LinkedHashMap<String, Boolean> timeRecords = dayOfWeek.getTimeRecords();
//                    timeRecords.put(keyParse.toLocalTime().toString(), false);
//                    dayOfWeek.setTimeRecords(timeRecords);
//                    realSchedule.put(date.toString(), dayOfWeek);
//                } else {
//                    realSchedule.put(date.toString(), schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)));
//                }
//            }
//
//        }
//
//
//        return realSchedule;
//    }
    public LinkedHashMap<String, DayOfWeek> twoWeeksSchedule() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDate dateNow = LocalDate.now();
        realSchedule = new LinkedHashMap<>();
        Locale locale = Locale.getDefault();
        for (int i = 0; i < 13; i++) {
            LocalDate date = dateNow.plusDays(i);
            realSchedule.put(date.toString(), schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)));

        }
        for (String key : records.keySet()) {
            LocalDateTime keyParse = LocalDateTime.parse(key, formatter);
            if (keyParse.isAfter(LocalDateTime.now())) {
                DayOfWeek dayOfWeek = realSchedule.get(keyParse.toLocalDate().toString());
                LinkedHashMap<String, Boolean> timeRecords = dayOfWeek.getTimeRecords();
                timeRecords.put(keyParse.toLocalTime().toString(), false);
                dayOfWeek.setTimeRecords(timeRecords);
                realSchedule.put(keyParse.toString(), dayOfWeek);
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

    public LinkedHashMap<String, DayOfWeek> getRealSchedule() {
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






