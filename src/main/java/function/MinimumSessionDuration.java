package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MinimumSessionDuration implements Function<List<SleepingSession>, Integer> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        Optional<Integer> min = sleepingSessionList
                .stream()
                .map(session -> Duration.between(session.getBedTime(), session.getWakeTime()).toMinutes())
                .min(Long::compareTo)
                .map(Long::intValue);
        return min.get();

    }
}
