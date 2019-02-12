package telran.java23.serviceprivder.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor

public class DayOfWeek {
    String name;
    Boolean isAvailable;
    LocalTime startDay;
    LocalTime endDay;
    Integer breakeInMinute;
    LocalTime breakStart;
    @Singular
    Set<Record> records;
    LinkedHashMap<String, Boolean> timeRecords;



    public DayOfWeek(String name, Boolean isAvailable, LocalTime startDay, LocalTime endDay, Integer breakeInMinute, LocalTime breakStart, Set<Record> records) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.startDay = startDay;
        this.endDay = endDay;
        this.breakeInMinute = breakeInMinute;
        this.breakStart = breakStart;
        this.records = records;
        this.timeRecords = new LinkedHashMap<>();
        if(startDay!=null & endDay!=null){
            mapa();
        }

    }



    public void mapa() {
        int lenthOfWorkDay = endDay.getHour()- startDay.getHour();
        LocalTime[] times=new LocalTime[lenthOfWorkDay+1];
        times[0]=startDay;
        for(int a=0;a<lenthOfWorkDay;a++){
            times[a+1]=times[a].plusMinutes(60);

        }
        for(int b=0;b<times.length-1;b++){
            if(times[b].equals(breakStart)){
                timeRecords.put(times[b].toString(),false);
            }else {
                timeRecords.put(times[b].toString(), true);
            }
        }
        System.out.println(Arrays.asList(timeRecords));




    }
}



