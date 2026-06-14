package function;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadSleepSession implements Function<List<SleepingSession>, Integer> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        long averageMinutes = sleepingSessionList
                .stream()
                .filter(sleepingSession -> sleepingSession.getSleepQuality() == SleepQuality.BAD)
                .count();
        return Math.toIntExact(averageMinutes);
    }
}
