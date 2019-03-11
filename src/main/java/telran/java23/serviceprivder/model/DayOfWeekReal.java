package telran.java23.serviceprivder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DayOfWeekReal {
    LinkedHashMap<String, Boolean> realTimeRecords;
    Boolean isAvailable;

    public DayOfWeekReal(DayOfWeek day) {
        realTimeRecords = new LinkedHashMap<>();
        this.realTimeRecords.putAll(day.getTimeRecords());
        isAvailable=day.getIsAvailable();
    }
}
