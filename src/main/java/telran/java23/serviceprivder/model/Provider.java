package telran.java23.serviceprivder.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java23.serviceprivder.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;

@Getter
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
    LinkedHashMap<LocalDateTime,Record>records;
    Schedule schedule;
    LinkedHashMap<LocalDate,DayOfWeek> realSchedule;
    Double vote;
    Set<String>comments;



    public LinkedHashMap <LocalDate,DayOfWeek> twoWeeksSchedule() {
        LocalDate dateNow = LocalDate.now();
        realSchedule = new LinkedHashMap<>();
        Locale locale = Locale.getDefault();
        for (int i = 0; i < 13; i++) {
            LocalDate date=dateNow.plusDays(i);
            realSchedule.put(date, schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)));

        }
        return realSchedule;
    }

}






