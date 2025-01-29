import java.util.List;

public class FoodsStatistics {
    private int count = 0;
    private double total;
    private double average;
    private double minimum;
    private double maximum;

    FoodsStatistics(List<Food> foods) {
        int count = setTotalFood(foods);
        total = setTotalValue(foods);
        average = setAverageValue(foods);
        minimum = setMinimumValue(foods);
        maximum = setMaximumValue(foods);
    }

    public int setTotalFood(List<Food> foods) {
        return foods.size();
    }

    public double setTotalValue(List<Food> foods) {
        double totalValue = 0;
        for (Food food : foods) {
            totalValue += food.getValue();
        }
        return (double) Math.round(totalValue * 100) / 100;
    }

    public double setAverageValue(List<Food> foods) {
        double sum = 0;
        for (Food food : foods) {
            sum += food.getValue();
        }
        return (double) Math.round(sum / foods.size() * 100) / 100;
    }

    public double setMinimumValue(List<Food> foods) {
        double min = Double.MAX_VALUE;
        for (Food food : foods) {
            if (food.getValue() < min) {
                min = food.getValue();
            }
        }
        return min;
    }

    public double setMaximumValue(List<Food> foods) {
        double max = 0;
        for (Food food : foods) {
            if (food.getValue() > max) {
                max = food.getValue();
            }
        }
        return max;
    }

    public double getTotal() {
        return total;
    }
    public double getAverage() {
        return average;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public int getCount() {
        return count;
    }
}
