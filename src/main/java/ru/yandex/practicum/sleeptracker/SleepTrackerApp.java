package ru.yandex.practicum.sleeptracker;

import exception.InputFileLoaderException;
import function.AmountOfSleepSessions;
import function.AverageSessionDuration;
import function.BadSleepSession;
import function.DefinitionTypeOfUser;
import function.MaximumSessionDuration;
import function.MinimumSessionDuration;
import function.NumberOfSleeplessNights;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static List<SleepingSession> sleepingSessionList;
    static List<Function<List<SleepingSession>, Integer>> functions = List.of(new AmountOfSleepSessions(),
            new MinimumSessionDuration(),
            new MaximumSessionDuration(),
            new AverageSessionDuration(),
            new BadSleepSession(),
            new NumberOfSleeplessNights(),
            new DefinitionTypeOfUser());

    public static void main(String[] args) {
        List<String> stringSleepingSessionList;
        try {
            stringSleepingSessionList = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        } catch (InputFileLoaderException | IOException e) {
            throw new RuntimeException(e);
        }
        sleepingSessionList = ParsingStringToSleepingSession.parseStringToSleepingSession(stringSleepingSessionList);
        printAnalyzeFunction();
        printInfo();

    }

    static void printAnalyzeFunction() {
        System.out.println("Запуск аналитических функций...");
    }

    static void printInfo() {
        List<Integer> allAnalyticsValues = functions.stream()
                .map(x -> x.apply(sleepingSessionList))
                .toList();
        SleepAnalysisResult.printInfo(allAnalyticsValues);
    }
}