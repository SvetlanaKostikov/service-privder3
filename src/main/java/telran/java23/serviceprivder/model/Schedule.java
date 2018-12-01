package telran.java23.serviceprivder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    DayOfWeek	sunday;
    DayOfWeek	monday;
    DayOfWeek	tuesday;
    DayOfWeek	wednesday;
    DayOfWeek	thursday;
    DayOfWeek	friday;
    DayOfWeek	saturday;

}
