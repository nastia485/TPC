
//Реалізуйте роботу електронного журналу групи,в якому зберігаються оцінки
// з однієї дисципліни трьох груп студентів. Кожного тижня лектор і його 3
// асистенти виставляють оцінки з дисципліни за 100-бальною шкалою.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal();
        Teacher lecturer = new Teacher("Lecturer", new int[]{1, 2, 3});
        Teacher assistant1 = new Teacher("Assistant1", new int[]{1});
        Teacher assistant2 = new Teacher("Assistant2", new int[]{2});
        Teacher assistant3 = new Teacher("Assistant3", new int[]{3});

        TeacherThread tt1 = new TeacherThread(lecturer, journal);
        TeacherThread tt2 = new TeacherThread(assistant1, journal);
        TeacherThread tt3 = new TeacherThread(assistant2, journal);
        TeacherThread tt4 = new TeacherThread(assistant3, journal);

        long startTime = System.currentTimeMillis();

        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();

        try {
            tt1.join();
            tt2.join();
            tt3.join();
            tt4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("It took "+ totalTime + " milliseconds");

        System.out.println(journal);

    }

}