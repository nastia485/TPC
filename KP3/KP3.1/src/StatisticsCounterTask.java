import java.util.*;
import java.util.concurrent.RecursiveTask;

public class StatisticsCounterTask extends RecursiveTask<StatisticsCounter> {
    String[] array;
    int threshold = 50;
    int start;
    int end;

    public StatisticsCounterTask(String[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }


    protected StatisticsCounter compute() {
        if (end - start < threshold) {
            return computeDirectly();
        } else {
            int middle = (end + start) / 2;

            StatisticsCounterTask subTask1 = new StatisticsCounterTask(array, start, middle);
            StatisticsCounterTask subTask2 = new StatisticsCounterTask(array, middle, end);

            invokeAll(subTask1, subTask2);

            StatisticsCounter leftResult = subTask1.compute();
            StatisticsCounter rightResult = subTask2.compute();


            return mergeStatistics(leftResult, rightResult);
        }
    }

    protected StatisticsCounter computeDirectly() {
        StatisticsCounter statisticsCounter = new StatisticsCounter();

        statisticsCounter.average = averageLength();
        //statisticsCounter.median = median();
        statisticsCounter.mode = mode();
        statisticsCounter.min = min();
        statisticsCounter.max = max();

        return statisticsCounter;
    }

    private double averageLength() {
        int sum = 0;
        for (String i : array) {
            sum += i.length();
        }
        return (double) sum / array.length;
    }

//    private double median() {
//        Arrays.sort(array, Comparator.comparingInt(String::length));
//
//        //Arrays.sort(array, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
//
//        if (array.length % 2 == 0) {
//            return (double) (array[(array.length / 2) - 1].length() + array[(array.length / 2)].length()) / 2;
//
//        } else return array[(int) Math.floor((double) array.length / 2)].length();
//    }

    private Map.Entry<String, Integer> mode() {
        Map<String, Integer> distribution = new HashMap<>();
        int findsNum;
        for (String i : array) {
            if (distribution.containsKey(i)) {
                findsNum = distribution.get(i) + 1;
            } else {
                findsNum = 1;
            }
            distribution.put(i, findsNum);
        }

        String mode = "";
        int maxValue = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : distribution.entrySet()) {
            if (entry.getValue() > maxValue) {
                mode = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        return new AbstractMap.SimpleEntry<>(mode, maxValue);
    }

    private Integer min() {
        //Arrays.sort(array, Comparator.comparingInt(String::length));
        int min = array[0].length();

        for (int i = 1; i < array.length; i++) {
            if (array[i].length() < min && array[i].length()!=0) {
                min = array[i].length();
            }
        }
        return min;
    }

    private Integer max() {
        int max = array[0].length();

        for (int i = 1; i < array.length; i++) {
            if (array[i].length() > max && array[i].length()!=0) {
                max = array[i].length();
            }
        }
        return max;
    }

    private StatisticsCounter mergeStatistics(StatisticsCounter left, StatisticsCounter right) {
        StatisticsCounter merged = new StatisticsCounter();
        merged.average = (left.average + right.average) / 2;
       // merged.median = (left.median + right.median) / 2;
        if (left.mode.getValue() > right.mode.getValue()) {
            merged.mode = left.mode;
        } else merged.mode = right.mode;
        merged.min = Math.min(left.min, right.min);
        merged.max = Math.max(left.max, right.max);
        return merged;
    }

}