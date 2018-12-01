package telran.java23.serviceprivder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeek {
    Boolean	isAvailable;
    ZonedDateTime startDay;
    ZonedDateTime	endDay;
    Integer	breakeInMinute;
    ZonedDateTime	breakStart;
    Record []	records;

}
