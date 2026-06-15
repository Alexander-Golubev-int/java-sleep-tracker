package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MaximumSessionDuration implements Function<List<SleepingSession>, Integer>, Consumer<List<SleepingSession>> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        Optional<Integer> max = sleepingSessionList
                .stream()
                .map(session -> Duration.between(session.getBedTime(), session.getWakeTime()).toMinutes())
                .max(Long::compareTo)
                .map(Long::intValue);
        return max.get();
    }

    @Override
    public void accept(List<SleepingSession> sleepingSessionList) {
        System.out.println("Максимальная продолжительность cессии (в минутах): " + apply(sleepingSessionList));
    }

}
