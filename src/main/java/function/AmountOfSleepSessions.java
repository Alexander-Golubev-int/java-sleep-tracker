package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AmountOfSleepSessions implements Function<List<SleepingSession>,Integer>, Consumer<List<SleepingSession>> {

   @Override
   public Integer apply(List<SleepingSession> sleepingSessionList) {
      return sleepingSessionList.size();
   }

   @Override
   public void accept(List<SleepingSession> sleepingSessionList) {
      System.out.println("Количество сессий сна:  " + apply(sleepingSessionList));
   }

}
