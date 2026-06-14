package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParsingStringToSleepingSession {


    public static List<SleepingSession> parseStringToSleepingSession(List<String> list) {

        return list
                .stream()
                .map(ParsingStringToSleepingSession::splitFormater)
                .toList();
    }

    private static SleepingSession splitFormater(String string) {
        DateTimeFormatter inputFormatter =
                DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        String[] line = string.split(";");
        LocalDateTime bedTime = LocalDateTime.parse(line[0], inputFormatter);
        LocalDateTime wakeTime = LocalDateTime.parse(line[1], inputFormatter);
        SleepQuality sleepQuality = SleepQuality.valueOf(line[2]);
        return new SleepingSession(bedTime, wakeTime, sleepQuality);
    }

}
