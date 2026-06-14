package ru.yandex.practicum.sleeptracker;

import exception.InputFileLoaderException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileWriter;

import java.util.List;
import java.util.stream.Collectors;

public class SleepingLogLoader {


    private SleepingLogLoader() {
    }

    public static List<String> loadSleepLoader(String nameOfFile) throws IOException, InputFileLoaderException {

        List<String> logOfSleeptoList;
        try (Reader reader = new FileReader(nameOfFile); BufferedReader bufferedReader = new BufferedReader(reader)) {
            logOfSleeptoList = bufferedReader.lines().collect(Collectors.toList());
            if (logOfSleeptoList.isEmpty()) {
                throw new InputFileLoaderException("Файл пуст.");
            }
        } catch (IOException | InputFileLoaderException e) {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("logLoader.txt", true))) {
                printWriter.write("================Ошибка====================\n");
                e.printStackTrace(printWriter);
                printWriter.write("================Конец======================\n");
                System.out.println(e.getMessage());
            } catch (IOException ex) {
                System.out.println("Не удалось записать лог:" + ex.getMessage());
            }
            throw e;
        }
        return logOfSleeptoList;
    }

}
