package function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class AmountOfSleepSessions implements Function <List<SleepingSession>,Integer>{

   @Override
   public Integer apply(List<SleepingSession> sleepingSessionList) {
      return sleepingSessionList.size();
   }
}
