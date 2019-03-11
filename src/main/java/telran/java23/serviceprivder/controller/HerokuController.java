package telran.java23.serviceprivder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HerokuController {

//    https://pacific-sierra-61961.herokuapp.com


    @GetMapping
    public String helloHeroku(){

        return "<h1>ScheduleService</h1>\n" +
                "<h4>v.1.1 from 01.03.2019</h4>\n" +
                "<a href=\"https://pacific-sierra-61961.herokuapp.com/swagger-ui.html#/\"><h2>Welcome to Swagger...</h2></a>" +
                "<a href=\"https://docs.google.com/document/d/e/2PACX-1vRowcDLW-J-mQaEEnz3emmuMuE4Q3GdK9h3TsvanvBCUtsm_tQ7UYt_H4IaPPe-MyywP6ymNzsWxRNb/pub\">" +
                "<h2>Api Documentation</h2></a>";
    }
}

