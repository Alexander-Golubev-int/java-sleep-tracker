package ru.yandex.practicum.sleeptracker;

import exception.InputFileLoaderException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import function.AverageSessionDuration;
import function.BadSleepSession;
import function.DefinitionTypeOfUser;
import function.MaximumSessionDuration;
import function.MinimumSessionDuration;
import function.NumberOfSleeplessNights;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SleepTrackerAppTest {

    @Test
    void testLogLoader() throws IOException, InputFileLoaderException {
        //Загрузка корректного лога;
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        boolean sizeUpdatedList = !list.isEmpty();
        Assertions.assertTrue(sizeUpdatedList);
    }

    @Test
    void testEmptyFileToLogLoader() {
        //Загрузка пустого лога;
        Exception exception = assertThrows(InputFileLoaderException.class, () ->
                SleepingLogLoader.loadSleepLoader("src/main/resources/empty_sleep_log.txt"));
        assertEquals("Файл пуст.", exception.getMessage());
    }

    @Test
    void testCorrectParsingString() throws InputFileLoaderException, IOException {
        //Проверка корректного парсинга строчки в тип LocalDateTime
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime expectedLocalDataTimeBedTime = LocalDateTime.parse("01.10.25 23:15", inputFormatter);
        LocalDateTime expectedLocalDataTimeWakeUp = LocalDateTime.parse("02.10.25 07:30", inputFormatter);

        String[] line = list.getFirst().split(";");
        LocalDateTime bedTime = LocalDateTime.parse(line[0], inputFormatter);
        LocalDateTime wakeTime = LocalDateTime.parse(line[1], inputFormatter);
        SleepQuality sleepQuality = SleepQuality.valueOf(line[2]);

        assertEquals(expectedLocalDataTimeBedTime, bedTime);
        assertEquals(expectedLocalDataTimeWakeUp, wakeTime);
        assertEquals(SleepQuality.GOOD, sleepQuality);
    }

    @Test
    void testAnalyticAmountOfSleepSessions() throws InputFileLoaderException, IOException {
        //Проверка корректной подсчета лога с 1 записью
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        int expected = 1;
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        assertEquals(expected, sleepingSession.size());
    }

    @Test
    void testAnalyticAmountOfSleepSessions13Strings() throws InputFileLoaderException, IOException {
        //Проверка корректной подсчета лога с 13 записями
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        int expected = 13;
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        assertEquals(expected, sleepingSession.size());
    }

    @Test
    void testMinimumSessionDurationWith1String() throws InputFileLoaderException, IOException {
        //Проверка подсчета минимального количества сна в минутах в 1 сессии
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 495;
        MinimumSessionDuration minimumSessionDuration = new MinimumSessionDuration();
        assertEquals(expected, minimumSessionDuration.apply(sleepingSession));
    }

    @Test
    void testMinimumSessionDurationWith13Strings() throws InputFileLoaderException, IOException {
        //Проверка подсчета минимального количества сна в минутах в 13 сессиях
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 45;
        MinimumSessionDuration minimumSessionDuration = new MinimumSessionDuration();
        assertEquals(expected, minimumSessionDuration.apply(sleepingSession));
    }

    @Test
    void testMaximumSessionDurationWith1String() throws InputFileLoaderException, IOException {
        //Проверка подсчета максимального количества сна в минутах в 1 сессии
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 495;
        MaximumSessionDuration maximumSessionDuration = new MaximumSessionDuration();
        assertEquals(expected, maximumSessionDuration.apply(sleepingSession));
    }

    @Test
    void testMaximumSessionDurationWith13Strings() throws InputFileLoaderException, IOException {
        //Проверка подсчета максимального количества сна в минутах в 13 сессииях
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 500;
        MaximumSessionDuration maximumSessionDuration = new MaximumSessionDuration();
        assertEquals(expected, maximumSessionDuration.apply(sleepingSession));
    }

    @Test
    void testAverageSessionDurationWith2Strings() throws InputFileLoaderException, IOException {
        //Проверка подсчета максимального количества сна в минутах в 2 сессииях
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_2_strings.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 452;
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        assertEquals(expected, averageSessionDuration.apply(sleepingSession));
    }

    @Test
    void testAverageSessionDurationWith13Strings() throws InputFileLoaderException, IOException {
        //Проверка подсчета максимального количества сна в минутах в 13 сессииях
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 345;
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        assertEquals(expected, averageSessionDuration.apply(sleepingSession));
    }

    @Test
    void testBadSleepSessionQuality1StringWithoutQuality() throws InputFileLoaderException, IOException {
        //Проверка что в сессии не будет найдена строчка с полем BAD
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 0;
        BadSleepSession badSleepSession = new BadSleepSession();
        assertEquals(expected, badSleepSession.apply(sleepingSession));
    }

    @Test
    void testBadSleepSessionQuality13StringWithQuality() throws InputFileLoaderException, IOException {
        //Проверка поиска наличия двух BAD сессий из 13 строк
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 2;
        BadSleepSession badSleepSession = new BadSleepSession();
        assertEquals(expected, badSleepSession.apply(sleepingSession));
    }

    @Test
    void testSearchZeroNoSleepNight() throws InputFileLoaderException, IOException {
        //Проверка поиска наличия 0 бессонных ночей
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_1_string.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 0;
        NumberOfSleeplessNights numberOfSleeplessNights = new NumberOfSleeplessNights();
        assertEquals(expected, numberOfSleeplessNights.apply(sleepingSession));
    }

    @Test
    void testSearchOneNoSleepNight() throws InputFileLoaderException, IOException {
        //Проверка поиска наличия 1 бессонной ночи
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/one_no_sleeping_night");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 1;
        NumberOfSleeplessNights numberOfSleeplessNights = new NumberOfSleeplessNights();
        assertEquals(expected, numberOfSleeplessNights.apply(sleepingSession));
    }

    @Test
    void testSearch20NoSleepNight() throws InputFileLoaderException, IOException {
        //Проверка поиска наличия 20 бессонных ночей
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 20;
        NumberOfSleeplessNights numberOfSleeplessNights = new NumberOfSleeplessNights();
        assertEquals(expected, numberOfSleeplessNights.apply(sleepingSession));
    }

    @Test
    void testSearchNoSleepNight() throws InputFileLoaderException, IOException {
        //Проверка поиска наличия бессонных ночей, когда дата конца лога переходит в другой месяц
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log_new_mounth");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 19;
        NumberOfSleeplessNights numberOfSleeplessNights = new NumberOfSleeplessNights();
        assertEquals(expected, numberOfSleeplessNights.apply(sleepingSession));
    }

    @Test
    void testDefinitionTypeOwl() throws InputFileLoaderException, IOException {
        //Проверка что по логу пользователь будет совой
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/owl_sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 1;
        DefinitionTypeOfUser definitionTypeOfUser = new DefinitionTypeOfUser();
        assertEquals(expected, definitionTypeOfUser.apply(sleepingSession));
    }

    @Test
    void testDefinitionTypeLark() throws InputFileLoaderException, IOException {
        //Проверка что по логу пользователь будет жаворонком
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/lark_sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 2;
        DefinitionTypeOfUser definitionTypeOfUser = new DefinitionTypeOfUser();
        assertEquals(expected, definitionTypeOfUser.apply(sleepingSession));
    }

    @Test
    void testDefinitionTypePigeonBecauseOwlAndLarsEquals() throws InputFileLoaderException, IOException {
        //Проверка что по логу пользователь будет голубем т.к. засыпаний с типом жаворонок будет равно сове
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/pigeon_sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 3;
        DefinitionTypeOfUser definitionTypeOfUser = new DefinitionTypeOfUser();
        assertEquals(expected, definitionTypeOfUser.apply(sleepingSession));
    }

    @Test
    void testDefinitionTypePigeon() throws InputFileLoaderException, IOException {
        //Проверка что по логу пользователь будет голубем
        List<String> list = SleepingLogLoader.loadSleepLoader("src/main/resources/sleep_log.txt");
        List<SleepingSession> sleepingSession = ParsingStringToSleepingSession.parseStringToSleepingSession(list);
        int expected = 3;
        DefinitionTypeOfUser definitionTypeOfUser = new DefinitionTypeOfUser();
        assertEquals(expected, definitionTypeOfUser.apply(sleepingSession));
    }
}