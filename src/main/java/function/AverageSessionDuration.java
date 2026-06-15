package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, Integer>, Consumer<List<SleepingSession>> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        long averageMinutes = sleepingSessionList
                .stream()
                .mapToLong(session -> Duration.between(session.getBedTime(), session.getWakeTime()).toMinutes())
                .sum();
        return Math.toIntExact(averageMinutes / sleepingSessionList.size());
    }

    @Override
    public void accept(List<SleepingSession> sleepingSessionList) {
        System.out.println("Минимальная продолжительность cессии (в минутах): " + apply(sleepingSessionList));
    }
}
