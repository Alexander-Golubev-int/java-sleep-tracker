package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class NumberOfSleeplessNights implements Function<List<SleepingSession>, Integer>, Consumer<List<SleepingSession>> {

    @Override
    public Integer apply(List<SleepingSession> sleepingSessionList) {
        LocalDate localStartDate = sleepingSessionList.getFirst().getWakeTime().toLocalDate();
        LocalDateTime lastWakeTime = sleepingSessionList.getLast().getWakeTime();
        LocalDate localEndDate = lastWakeTime.toLocalDate();
        LocalTime localTimeBed = LocalTime.of(0, 0);
        LocalTime localTimeWake = LocalTime.of(6, 0);

        return Math.toIntExact(Stream.iterate(localStartDate,
                        date -> !date.isAfter(localEndDate),
                        date -> date.plusDays(1))
                .filter(date -> {
                    boolean slept = sleepingSessionList.stream()
                            .anyMatch(session -> {
                                LocalDateTime localDateTime = LocalDateTime.of(date, localTimeWake);
                                if (session.getBedTime().isBefore(localDateTime)) {
                                    localDateTime = LocalDateTime.of(date, localTimeBed);
                                    if (session.getWakeTime().isAfter(localDateTime)) {
                                        return true;
                                    }
                                }
                                return false;
                            });

                    return !slept;
                })
                .count());
    }

    @Override
    public void accept(List<SleepingSession> sleepingSessionList) {
        System.out.println("Количество бессонных ночей: " + apply(sleepingSessionList));
    }
}

