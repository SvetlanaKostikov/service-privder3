package telran.java23.serviceprivder.service;

import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.ProviderRegisterDto;
import telran.java23.serviceprivder.dto.ScheduleDto;
import telran.java23.serviceprivder.model.DayOfWeek;
import telran.java23.serviceprivder.model.Provider;
import telran.java23.serviceprivder.model.Record;
import telran.java23.serviceprivder.model.Schedule;

import java.util.Map;
import java.util.Set;

public interface ProviderService {
    public ProviderDto addNewProvider(ProviderRegisterDto newProvider);
    public String deleteProvider(String email);
    public Schedule createSchedule(String email, ScheduleDto schedule);
    public ProviderDto updateProvider(String email,ProviderDto provider);
    public boolean login(String auth);
    public ProviderDto showProfileProvider(String email);
    public Schedule deleteSchedule(String email);
    public Set<Provider> showAllProviders();
    public Schedule showSchedule(String email);
    public Map<String, DayOfWeek> showRealSchedule(String email);
    public Set<String> showAllClientsForProvider(String email);
    public Schedule updateSchedule(String email, ScheduleDto schedule);
    public Set<Record>showAllrecordsForDay(String email, String date);
    public Set<Record> showArchiveRecords(String email);
    public Set<Record> showAllrecords(String email);


}

