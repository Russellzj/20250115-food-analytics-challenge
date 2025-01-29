import java.time.LocalDateTime;
import java.util.*;

public class Foods {
    private List<Food> foods = new ArrayList<>();
    private Map<String, List<Food>> categories = new HashMap<>();
    private Map<String, HashSet<String>> categoryAndSubCategory = new HashMap<>();
    private Map<String, List<Food>> subCategories = new HashMap<>();

    public void addFood(String foodData) {
        Food newFood = importData(foodData);
        if (newFood != null) {
            foods.add(newFood);
            categories.putIfAbsent(newFood.getCategory(), new ArrayList<>());
            categories.get(newFood.getCategory()).add(newFood);
            subCategories.putIfAbsent(newFood.getSubcategory(), new ArrayList<>());
            subCategories.get(newFood.getSubcategory()).add(newFood);
            categoryAndSubCategory.putIfAbsent(newFood.getCategory(), new HashSet<>());
            categoryAndSubCategory.get(newFood.getCategory()).add(newFood.getSubcategory());
        }
    }

    private Food importData(String data) {
        Food food  = new Food();
        String[] foodData = data.split(",");
        for (int i = 0; i < foodData.length; i++) {
            foodData[i] = foodData[i].replaceAll("^\"|\"$|^\'|\'$", "");
            foodData[i] = foodData[i].trim();
        }
        if (foodData.length == 5) {
            if(foodData[0].matches("\\d+")) {
                food.setId(Integer.parseInt(foodData[0]));
            } else {
                return null;
            }
            if (foodData[1].matches("[A-Za-z ]+")) {
                food.setCategory(foodData[1]);
            } else {
                return null;
            }
            if (foodData[2].matches("[A-Za-z ]+")) {
                food.setSubcategory(foodData[2]);
            } else {
                return null;
            }
            if (foodData[3].matches("\\$?\\d+.\\d{0,2}")) {
                food.setValue(Double.parseDouble(foodData[3].replaceAll("^\\$", "")));
            } else {
                return null;
            }
            if (foodData[4].matches(
                    "([0-9]{4})-?(1[0-2]|0[1-9])-?(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):" +
                            "?([0-5][0-9]):?([0-5][0-9])")) {
                food.setDate(LocalDateTime.parse(foodData[4]));
            } else {
                return null;
            }
            return food;
        }
        return null;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public int getCount() {
        return foods.size();
    }


    //The following methods get the total amount of Foods
    public int getTotalCategory(String category) {
        if (categories.containsKey(category)) {
            return categories.get(category).size();
        }
        return 0;
    }

    public int getTotalSubCategory(String sub) {
        if (subCategories.containsKey(sub)) {
            return subCategories.get(sub).size();
        }
        return 0;
    }
    //End of total amount of Foods

    //The Following Methods get Total Values
    public double getTotalValue() {
        double totalValue = 0;
        for (Food food : foods) {
            totalValue += food.getValue();
        }
        return (double) Math.round(totalValue * 100) / 100;
    }

    private double getTotalValueFromFoods(List<Food> foods) {
        double totalValue = 0;
        for (Food food : foods) {
            totalValue += food.getValue();
        }
        return (double) Math.round(totalValue * 100) / 100;
    }

    public double getTotalValueCategory(String category) {
        if (categories.containsKey(category)) {
            return getTotalValueFromFoods(categories.get(category));
        }
        return 0;
    }

    public double getTotalValueSubCategory(String sub) {
        if (subCategories.containsKey(sub)) {
            return getTotalValueFromFoods(subCategories.get(sub));
        }
        return 0;
    }
    //End of Total Values

    //The following get Avg Value of Food
    public double getAverageValue() {
        double sum = 0;
        for (Food food : foods) {
            sum += food.getValue();
        }
        return (double) Math.round(sum / foods.size() * 100) / 100;
    }

    private double getAverageValueFromFoods(List<Food> foods) {
        double sum = 0;
        for (Food food : foods) {
            sum += food.getValue();
        }
        return (double) Math.round(sum / foods.size() * 100) / 100;
    }

    public double getAvgCategoryValue(String category) {
        if (categories.containsKey(category)) {
            return getAverageValueFromFoods(categories.get(category));
        }
        return 0;
    }

    public double getAvgSubCategoryValue(String sub) {
        if (subCategories.containsKey(sub)) {
            return getAverageValueFromFoods(subCategories.get(sub));
        }
        return 0;
    }
    //End of Avg Value of Food

    //The Following methods get Minimum value of Foods
    public double getMinimumValue() {
        double min = Double.MAX_VALUE;
        for (Food food : foods) {
            if (food.getValue() < min) {
                min = food.getValue();
            }
        }
        return min;
    }

    private double getMinimumValueFromFoods(List<Food> foods) {
        double min = Double.MAX_VALUE;
        for (Food food : foods) {
            if (food.getValue() < min) {
                min = food.getValue();
            }
        }
        return min;
    }

    public double getMinimumValueCategory(String category) {
        if (categories.containsKey(category)) {
            return getMinimumValueFromFoods(categories.get(category));
        }
        return 0;
    }

    public double getMinimumValueSubcategory(String subcategory) {
        if (subCategories.containsKey(subcategory)) {
            return getMinimumValueFromFoods(subCategories.get(subcategory));
        }
        return 0;
    }
    //End of Minimum Value of Foods

    //The following methods get Maximum Value of Foods
    public double getMaximumValue() {
        double max = 0;
        for (Food food : foods) {
            if (food.getValue() > max) {
                max = food.getValue();
            }
        }
        return max;
    }

    private double getMaximumValueFromFoods(List<Food> foods) {
        double max = 0;
        for (Food food : foods) {
            if (food.getValue() > max) {
                max = food.getValue();
            }
        }
        return max;
    }

    public double getMaximumValueCategory(String category) {
        if (categories.containsKey(category)) {
            return getMaximumValueFromFoods(categories.get(category));
        }
        return 0;
    }

    public double getMaximumValueSubCategory(String subcategory) {
        if (subCategories.containsKey(subcategory)) {
            return getMaximumValueFromFoods(subCategories.get(subcategory));
        }
        return 0;
    }
    //End of Maximum Value of Foods


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Overall: Total: " + getTotalValue() +
                " Min: " + getMinimumValue() + " Max: " + getMinimumValue() +
                " Avg: " + getAverageValue() + "\n"
        );
        for (String category : categories.keySet()) {
            sb.append("\tCategory: " + category + ", Total: " + getTotalValueCategory(category) +
                    ", Min: " + getMinimumValueCategory(category) + " Max: " + getMaximumValueCategory(category) +
                    ", Avg: " + getAvgCategoryValue(category) + "\n");
            for (String sub : categoryAndSubCategory.get(category)) {
                sb.append("\t\tSubcategory: " + sub + ", Total: " + getTotalValueSubCategory(sub) +
                        ", Min: " + getMinimumValueSubcategory(sub) + " Max: " + getMaximumValueSubCategory(sub) +
                        ", Avg: " + getAvgSubCategoryValue(sub) + "\n");
            }
        }
        return sb.toString();
    }
}