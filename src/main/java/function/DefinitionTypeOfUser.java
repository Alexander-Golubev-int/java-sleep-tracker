package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class DefinitionTypeOfUser implements Function<List<SleepingSession>, Integer> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        long owl = sleepingSessionList
                .stream()
                .filter(this::isOwl)
                .count();

        long lark = sleepingSessionList
                .stream()
                .filter(this::isLark)
                .count();

        long pigeon = sleepingSessionList
                .stream()
                .filter(this::isPigeon)
                .count();

        if (owl > lark && owl > pigeon) {
            return 1;
        } else if (lark > owl && lark > pigeon) {
            return 2;
        }
        return 3;
    }


    private boolean isOwl(SleepingSession s) {
        return s.getBedTime().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                s.getWakeTime().toLocalTime().isAfter(LocalTime.of(9, 0));
    }

    private boolean isLark(SleepingSession s) {
        return s.getBedTime().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                s.getWakeTime().toLocalTime().isBefore(LocalTime.of(7, 0));
    }

    private boolean isPigeon(SleepingSession s) {
        return !isOwl(s) && !isLark(s);
    }
}
