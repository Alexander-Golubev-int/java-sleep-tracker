package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, Integer> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        long averageMinutes = sleepingSessionList
                .stream()
                .mapToLong(session -> Duration.between(session.getBedTime(), session.getWakeTime()).toMinutes())
                .sum();
        return Math.toIntExact(averageMinutes / sleepingSessionList.size());
    }
}
