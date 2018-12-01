package telran.java23.serviceprivder.model;

import java.time.ZonedDateTime;

public class Record {
    Long	id;
    ZonedDateTime	startService;
    ZonedDateTime endService;
    Service[]	service;
    String	comment;
    Client	client;
    Provider provider;


}
