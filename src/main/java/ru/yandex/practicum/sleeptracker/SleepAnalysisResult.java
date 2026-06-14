package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SleepAnalysisResult {
    private static List<String> allAnalyticsValues;
    private static List<String> analyzeList = new ArrayList<>();

    public SleepAnalysisResult() {
    }

    public void addNewAnalyze(String text) {
        if (!analyzeList.contains(text)) {
            analyzeList.add(text);
        }
    }

    public static void printInfo(List<Integer> listOfValues) {
        analyzeList.add("Количество сессий сна: ");
        analyzeList.add("Минимальная продолжительность cессии (в минутах): ");
        analyzeList.add("Максимальная продолжительность cессии (в минутах): ");
        analyzeList.add("Средняя продолжительность сна (в минутах): ");
        analyzeList.add("Количество сессий с плохим качеством сна: ");
        analyzeList.add("Количество бессонных ночей: ");
        analyzeList.add("Люди делятся по типам сна, где:\n" +
                "1 - Сова\n" +
                "2 - Жаворонок\n" +
                "3 - Голубь\n" +
                "Ваш тип соответствует:  ");

        AtomicInteger i = new AtomicInteger(0);
        allAnalyticsValues = new ArrayList<>(analyzeList);
        allAnalyticsValues = allAnalyticsValues.stream()
                .map(x -> x + listOfValues.get(i.getAndIncrement()))
                .toList();
        allAnalyticsValues.forEach(System.out::println);
    }


}
