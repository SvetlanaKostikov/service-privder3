package telran.java23.serviceprivder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayOfWeekDto {
    Boolean	isAvailable;
    ZonedDateTime startDay;
    ZonedDateTime	endDay;
    Integer	breakeInMinute;
    ZonedDateTime	breakStart;


}
