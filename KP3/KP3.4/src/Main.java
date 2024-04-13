
//Реалізуйте роботу електронного журналу групи,в якому зберігаються оцінки
// з однієї дисципліни трьох груп студентів. Кожного тижня лектор і його 3
// асистенти виставляють оцінки з дисципліни за 100-бальною шкалою.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal(3);

        EnteringTask mainTask = new EnteringTask(journal, 0, journal.getStudNum());
        long timeBefore = System.currentTimeMillis();
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(mainTask);
        }
        long timeAfter = System.currentTimeMillis();

        System.out.println("It took "+ (timeAfter-timeBefore) + " milliseconds");

        System.out.println(journal);

    }

}