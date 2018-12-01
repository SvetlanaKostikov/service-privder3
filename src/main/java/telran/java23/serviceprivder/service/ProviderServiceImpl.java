package telran.java23.serviceprivder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java23.serviceprivder.configuration.AccountConfiguration;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.DayOfWeekDto;
import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.ProviderRegisterDto;
import telran.java23.serviceprivder.dto.ScheduleDto;
import telran.java23.serviceprivder.exeptions.UserExistEcxeption;
import telran.java23.serviceprivder.model.AccountUserCredential;
import telran.java23.serviceprivder.model.DayOfWeek;
import telran.java23.serviceprivder.model.Provider;
import telran.java23.serviceprivder.model.Schedule;

import java.util.Set;
import java.util.stream.Collectors;


@Service

public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AccountConfiguration accountConfiguration;


    @Override
    public ProviderDto addNewProvider(ProviderRegisterDto newProvider) {
        if (providerRepository.existsById(newProvider.getEmail())) {
            throw new UserExistEcxeption("Code: 409. User already exist!");

        }
        String hashPassword = encoder.encode(newProvider.getPassword());
        Provider provider = new Provider(newProvider.getEmail(), hashPassword, newProvider.getProfession(),
                newProvider.getFirstName(), newProvider.getLastName(), newProvider.getTelephone(), newProvider.getWhatsApp(),
                newProvider.getAddress(), false, newProvider.getServices(), null, null,null,null);
        ProviderDto providerDto = new ProviderDto(newProvider.getProfession(),newProvider.getFirstName(),newProvider.getLastName(),
                newProvider.getTelephone(),newProvider.getWhatsApp(),newProvider.getAddress(),newProvider.getIsActive(),
                newProvider.getServices());
        providerRepository.save(provider);
        return providerDto;
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
        return scheduleIs;
    }


    public DayOfWeek convertToDayOfWeek(DayOfWeekDto dayOfWeekDto) {
        DayOfWeek day = new DayOfWeek(dayOfWeekDto.getIsAvailable(), dayOfWeekDto.getStartDay(), dayOfWeekDto.getEndDay(),
                dayOfWeekDto.getBreakeInMinute(), dayOfWeekDto.getBreakStart(), null);
        return day;
    }

    @Override
    public Provider updateProvider(String email, ProviderDto providerDto) {
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
        return provider;
    }

    @Override
    public boolean login(String auth) {//TODO - razobratsya s exeptions
        AccountUserCredential credentials = accountConfiguration.tokens(auth);
        Provider provider = providerRepository.findById(credentials.getLogin()).get();
        String hashPassword = encoder.encode(credentials.getPassword());
        if(!hashPassword.equals(provider.getPassword())){
            return false;
        }

        return true;

    }
    @Override
    public ProviderDto showProfileProvider(String email){
        Provider provider = providerRepository.findById(email).get();
        ProviderDto providerDto = new ProviderDto(provider.getProfession(),provider.getFirstName(),provider.getLastName(),
                provider.getTelephone(),provider.getWhatsApp(),provider.getAddress(),provider.getIsActive(),provider.getServices());
        return providerDto;
    }

    @Override
    public Schedule deleteSchedule(String email) {
        Provider provider = providerRepository.findById(email).get();
        DayOfWeek dayOfWeek = new DayOfWeek();
        Schedule schedule = new Schedule(dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek,dayOfWeek);
        provider.setSchedule(schedule);
        providerRepository.save(provider);
        return schedule;
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

}



