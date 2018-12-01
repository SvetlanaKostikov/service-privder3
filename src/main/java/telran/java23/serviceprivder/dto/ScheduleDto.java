package telran.java23.serviceprivder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScheduleDto {
    DayOfWeekDto sunday;
    DayOfWeekDto	monday;
    DayOfWeekDto	tuesday;
    DayOfWeekDto	wednesday;
    DayOfWeekDto	thursday;
    DayOfWeekDto	friday;
    DayOfWeekDto	saturday;
}
