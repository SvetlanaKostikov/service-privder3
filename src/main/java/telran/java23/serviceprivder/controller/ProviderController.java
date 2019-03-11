package telran.java23.serviceprivder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.java23.serviceprivder.dao.ProviderRepository;
import telran.java23.serviceprivder.dto.ProviderDto;
import telran.java23.serviceprivder.dto.ProviderRegisterDto;
import telran.java23.serviceprivder.dto.ScheduleDto;
import telran.java23.serviceprivder.model.*;
import telran.java23.serviceprivder.service.ProviderService;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;
    @Autowired
    ProviderRepository providerRepository;

    @PostMapping("/register")
    public ProviderDto createNewProvider(@RequestBody ProviderRegisterDto provider){
        return providerService.addNewProvider(provider);

    }
    @DeleteMapping("/{email}")
    public String deleteProvider(@PathVariable String email){
        return providerService.deleteProvider(email);
    }

    @PostMapping("/schedule/{email}")
    public Schedule createSchedule(@PathVariable String email, @RequestBody ScheduleDto scheduleDto){
        return providerService.createSchedule(email,scheduleDto);
    }
    @PostMapping("/updateschedule/{email}")
    public Schedule updateSchedule(@PathVariable String email, @RequestBody ScheduleDto schedule){
        return providerService.updateSchedule(email,schedule);
    }
    @PutMapping("/update/{email}")
    public ProviderDto updateProvider(@PathVariable String email,@RequestBody ProviderDto provider){
        return providerService.updateProvider(email,provider);
    }
    @GetMapping("/login")
    public boolean login(@RequestHeader(value = "Authorization")String auth){
        return providerService.login(auth);
    }

    @GetMapping("/profile/{email}")
    public ProviderDto showProfileProvider(@PathVariable String email){
        return providerService.showProfileProvider(email);
    }
    @GetMapping("/providers")
    public Set<Provider> showAllProviders(){
        return providerService.showAllProviders();
    }

    @GetMapping("/schedule/{email}")
    public Schedule showSchedule(@PathVariable String email){
        return providerService.showSchedule(email);
    }

    @GetMapping("/realschedule/{email}")
    public Map<String, DayOfWeekReal> showRealSchedule(@PathVariable String email){
        return providerService.showRealSchedule(email);
    }
    @DeleteMapping("/schedule/{email}")
    public Schedule deleteSchedule(@PathVariable String email) {
        return providerService.deleteSchedule(email);
    }

    @GetMapping("/showclients/{email}")
    public Set<String> showAllClientsForProvider(@PathVariable String email){
        return providerService.showAllClientsForProvider(email);
    }
    @GetMapping("/showrecords/{email}/{date}")
    public Set<Record>showAllrecordsForDay(@PathVariable String email,@PathVariable String date){
        return providerService.showAllrecordsForDay(email,date);
    }
    @GetMapping("/archiverecords/{email}/")
    public Set<Record> showArchiveRecords(@PathVariable String email){
        return providerService.showArchiveRecords(email);
    }
    @GetMapping("/showrecords/{email}")
    public Set<Record>showAllrecords(@PathVariable String email){
        return providerService.showAllrecords(email);
    }

}

