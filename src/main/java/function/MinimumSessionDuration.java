package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MinimumSessionDuration implements Function<List<SleepingSession>, Integer>, Consumer<List<SleepingSession>> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        Optional<Integer> min = sleepingSessionList
                .stream()
                .map(session -> Duration.between(session.getBedTime(), session.getWakeTime()).toMinutes())
                .min(Long::compareTo)
                .map(Long::intValue);
        return min.get();

    }

    @Override
    public void accept(List<SleepingSession> sleepingSessionList) {
        System.out.println("Минимальная продолжительность cессии (в минутах): " + apply(sleepingSessionList));
    }
}
