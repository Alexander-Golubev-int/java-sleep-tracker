package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;


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
}
