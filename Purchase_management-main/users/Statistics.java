package users;

public class Statistics {

    private User user;
    private String maxSpendingCategory;
    private String minSpendingCategory;
    private double averageDailySpending;

    public Statistics(User user) {
        this.user = user;
    }

    public void calculateStatistics() {
        double totalFun = user.getFunCategory().getTotal();
        double totalFood = user.getFoodCategory().getTotal();
        double totalServices = user.getServicesCategory().getTotal();

        maxSpendingCategory = "Category with maximum spending"; // Replace with the corresponding category
        minSpendingCategory = "Category with minimum spending"; // Replace with the corresponding category

        // Calculate the average daily spending
        int totalDays = 30;
        averageDailySpending = (totalFun + totalFood + totalServices) / totalDays;
    }

    public String getMaxSpendingCategory() {
        return maxSpendingCategory;
    }

    public String getMinSpendingCategory() {
        return minSpendingCategory;
    }

    public double getAverageDailySpending() {
        return averageDailySpending;
    }
}
