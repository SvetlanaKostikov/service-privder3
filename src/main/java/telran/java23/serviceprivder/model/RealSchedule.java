package telran.java23.serviceprivder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealSchedule {
    LinkedHashMap<LocalDate,DayOfWeek> realschedule;

//    public LinkedHashMap <LocalDate,DayOfWeek> getTwoWeeksSchedule(Provider provider) {
//        Schedule schedule = provider.getSchedule();
//        LocalDate dateNow = LocalDate.now();
//        realschedule = new LinkedHashMap<>();
//        Locale locale = Locale.getDefault();
//        for (int i = 0; i < 13; i++) {
//            LocalDate date=dateNow.plusDays(i);
//            realschedule.put(date, schedule.findDay(date.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)));
//
//        }
//        System.out.println(Arrays.asList(realschedule));
//        return realschedule;
//    }

}
