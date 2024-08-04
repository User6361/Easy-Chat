package dev.estuvium.utilities;//function for get data time for text in server

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetTime{
    public String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:HH:mm:ss");
        String formattedNow = now.format(formatter);
        return "[" + formattedNow + "] ";
    }
}