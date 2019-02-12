package telran.java23.serviceprivder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Schedule {
    DayOfWeek sunday;
    DayOfWeek monday;
    DayOfWeek tuesday;
    DayOfWeek wednesday;
    DayOfWeek thursday;
    DayOfWeek friday;
    DayOfWeek saturday;

    public DayOfWeek findDay(String name) {
        DayOfWeek[] days = {sunday, monday, tuesday, wednesday, thursday, friday, saturday};
        for (int i = 0; i < days.length; i++) {
            if (days[i].getName().equalsIgnoreCase(name)) {
                return days[i];
            }
        }
        return null;

    }
}

