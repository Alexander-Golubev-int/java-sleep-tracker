package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime bedTime;
    private LocalDateTime wakeTime;
    private SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime bedTime, LocalDateTime wakeTime, SleepQuality sleepQuality) {
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.sleepQuality = sleepQuality;
    }

    public LocalDateTime getBedTime() {
        return bedTime;
    }

    public LocalDateTime getWakeTime() {
        return wakeTime;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
