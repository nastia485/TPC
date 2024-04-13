import java.util.Map;

public class StatisticsCounter {
    Double average;

    //Double median;

    Map.Entry<String, Integer> mode;

    Integer min;
    Integer max;

    @Override
    public String toString() {
        String formattedAverage = String.format("%.2f", average);
        return "Statistic Results" +
                "\n Average length: " + formattedAverage +
                "\n Mode: word \"" + mode.getKey() + "\" occurs " + mode.getValue() + " times" +
                "\n Length min: " + min +
                "\n Length max: " + max;
    }
}
