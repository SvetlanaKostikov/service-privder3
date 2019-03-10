package telran.java23.serviceprivder.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import telran.java23.serviceprivder.configuration.AccountConfiguration;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.DayOfWeekDto;
import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.ProviderRegisterDto;
import telran.java23.serviceprivder.dto.ScheduleDto;
import telran.java23.serviceprivder.exeptions.UserExistEcxeption;
import telran.java23.serviceprivder.model.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;

//    @Autowired
//    PasswordEncoder encoder;

    @Autowired
    AccountConfiguration accountConfiguration;


    @Override
    public ProviderDto addNewProvider(ProviderRegisterDto newProvider) {
        if (providerRepository.existsById(newProvider.getEmail())) {
            throw new UserExistEcxeption("User already exist!");

        }
//        String hashPassword = encoder.encode(newProvider.getPassword());
        Set<Service> services = newProvider.getServices();
        Provider provider = new Provider(newProvider.getEmail(), newProvider.getPassword(), newProvider.getProfession(),
                newProvider.getFirstName(), newProvider.getLastName(), newProvider.getTelephone(), newProvider.getWhatsApp(),
                newProvider.getAddress(), true, services, new LinkedHashMap<>(),null,null, new ArrayList<>(),0.0);
        providerRepository.save(provider);
        return providerToProviderDto(provider);
    }

    @Override
    public String deleteProvider(String email) {
        Provider provider = providerRepository.findById(email).get();
        providerRepository.delete(provider);
        return "Provider is deleted";
    }

    @Override
    public Schedule createSchedule(String email, ScheduleDto schedule) {
            DayOfWeek sunday = convertToDayOfWeek(schedule.getSunday());
            DayOfWeek monday = convertToDayOfWeek(schedule.getMonday());
            DayOfWeek tuesday = convertToDayOfWeek(schedule.getTuesday());
            DayOfWeek wednesday = convertToDayOfWeek(schedule.getWednesday());
            DayOfWeek thursday = convertToDayOfWeek(schedule.getThursday());
            DayOfWeek friday = convertToDayOfWeek(schedule.getFriday());
            DayOfWeek saturday = convertToDayOfWeek(schedule.getSaturday());
            Schedule scheduleIs = new Schedule(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
            Provider provider = providerRepository.findById(email).get();
            provider.setSchedule(scheduleIs);
            providerRepository.save(provider);
            provider.twoWeeksSchedule();
            providerRepository.save(provider);
            return scheduleIs;
        }
    @Override
    public Schedule updateSchedule(String email, ScheduleDto schedule) {
        DayOfWeek sunday = convertToDayOfWeek(schedule.getSunday());
        DayOfWeek monday = convertToDayOfWeek(schedule.getMonday());
        DayOfWeek tuesday = convertToDayOfWeek(schedule.getTuesday());
        DayOfWeek wednesday = convertToDayOfWeek(schedule.getWednesday());
        DayOfWeek thursday = convertToDayOfWeek(schedule.getThursday());
        DayOfWeek friday = convertToDayOfWeek(schedule.getFriday());
        DayOfWeek saturday = convertToDayOfWeek(schedule.getSaturday());
        Schedule scheduleIs = new Schedule(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
        Provider provider = providerRepository.findById(email).get();
        provider.setSchedule(scheduleIs);
        providerRepository.save(provider);
        provider.twoWeeksSchedule();
        providerRepository.save(provider);
        return scheduleIs;
    }



    public DayOfWeek convertToDayOfWeek(DayOfWeekDto dayOfWeekDto) {
        if(dayOfWeekDto==null) {
            return null;
        }

        DayOfWeek day = new DayOfWeek(dayOfWeekDto.getName(),dayOfWeekDto.getIsAvailable(),dayOfWeekDto.getStartDay(), dayOfWeekDto.getEndDay(),
                dayOfWeekDto.getBreakeInMinute(), dayOfWeekDto.getBreakStart());
        return day;
    }

    @Override
    public ProviderDto updateProvider(String email, ProviderDto providerDto) {
        Provider provider = providerRepository.findById(email).get();
        provider.setFirstName(providerDto.getFirstName());
        provider.setLastName(providerDto.getLastName());
        provider.setProfession(providerDto.getProfession());
        provider.setTelephone(providerDto.getTelephone());
        provider.setWhatsApp(providerDto.getWhatsApp());
        provider.setAddress(providerDto.getAddress());
        provider.setIsActive(providerDto.getIsActive());
        provider.setServices(providerDto.getServices());
        providerRepository.save(provider);
        return providerToProviderDto(provider);
    }

    @Override
    public boolean login(String auth) {//TODO - razobratsya s exeptions
        AccountUserCredential credentials = accountConfiguration.tokens(auth);
        Provider provider = providerRepository.findById(credentials.getLogin()).get();
        String hashPassword = credentials.getPassword();
        if(!hashPassword.equals(provider.getPassword())){
            return false;
        }

        return true;

    }
    @Override
    public ProviderDto showProfileProvider(String email){
        Provider provider = providerRepository.findById(email).get();
        return providerToProviderDto(provider);
    }

    ProviderDto providerToProviderDto(Provider provider) {
        ProviderDto providerDto = new ProviderDto(provider.getProfession(),provider.getFirstName(),provider.getLastName(),
                provider.getTelephone(),provider.getWhatsApp(),provider.getAddress(),provider.getIsActive(),provider.getServices(),
                provider.getAverageVote());
        return providerDto;
    }

    @Override
    public Schedule deleteSchedule(String email) {
        DayOfWeek dayOfWeek=new DayOfWeek(null,false,null,null,0,null);
        Schedule schedule = new Schedule(dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek);
        Provider provider = providerRepository.findById(email).orElse(null);
        provider.setSchedule(schedule);
        providerRepository.save(provider);
        return provider.getSchedule();
    }

    @Override
    public Set<Provider> showAllProviders(){
        return providerRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Schedule showSchedule(String email){
        Provider provider = providerRepository.findById(email).get();
        return provider.getSchedule();
    }
    @Override
    public Map<String,DayOfWeek> showRealSchedule(String email){
        Provider provider=providerRepository.findById(email).get();
        return  provider.getRealSchedule();

    }
    @Override
    public Set<String> showAllClientsForProvider(String email){
        Provider provider = providerRepository.findById(email).get();
        LinkedHashMap<String,Record>records=provider.getRecords();
        HashSet<String> clientsSet=new HashSet<>();
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            clientsSet.add(entry.getValue().getEmailClient());
        }
        return clientsSet;

    }
    @Override
    public Set<Record>showAllrecordsForDay(String email,String date){
        LocalDate dateForm = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        HashSet<Record> recordsSet=new HashSet<>();
        Provider provider = providerRepository.findById(email).orElse(null);
        LinkedHashMap<String,Record>records=provider.getRecords();
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            LocalDateTime dateTime = LocalDateTime.parse(entry.getKey(), formatter);
            if(dateTime.toLocalDate().equals(dateForm)){
                recordsSet.add(entry.getValue());
            }
        }
        return recordsSet;
    }
    @Override
    public Set<Record> showArchiveRecords(String email){
        Provider provider=providerRepository.findById(email).get();
        HashSet<Record> onlyRecords=new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LinkedHashMap<String,Record>records=provider.getRecords();
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            LocalDateTime key = LocalDateTime.parse(entry.getKey(), formatter);
            if(key.isBefore(LocalDateTime.now())){
                onlyRecords.add(entry.getValue());
            }
        }
        return onlyRecords;
    }
    @Override
    public Set<Record> showAllrecords(String email) {
        Provider provider=providerRepository.findById(email).get();
        HashSet<Record> onlyRecords = new HashSet<>();
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LinkedHashMap<String, Record> records = provider.getRecords();
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            LocalDateTime key = LocalDateTime.parse(entry.getKey(), formatter);
            if (key.isAfter(dateNow)) {
                onlyRecords.add(entry.getValue());
            }
        }
        return onlyRecords;
    }




}



