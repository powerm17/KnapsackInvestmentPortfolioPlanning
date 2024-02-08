import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyPortfolioPlanning {
    public static List<Stock> greedySelectStocks(List<Stock> stocks, double budget) {
        // Calculate profitability for each stock 
        //This loop iterates over each stock in the list and calculates the profitability
        for (Stock stock : stocks) {
            stock.setpProb(stock.getpProb() * (stock.getpN() - stock.getPrice()));
        }

        // Sort stocks in descending order of profitability
        Collections.sort(stocks, Comparator.comparingDouble(stock -> stock.getpProb()));
        Collections.reverse(stocks);

        // Initialize variables
        List<Stock> selectedStocks = new ArrayList<>();
        double remainingBudget = budget;

        // Greedy selection
        // For each stock, it checks if adding the stock to the selected list would keep the total cost within the remaining budget. 
        // If yes, the stock is added to the selectedStocks list, and its cost is subtracted from the remaining budget.
        for (Stock stock : stocks) {
            if (stock.getPrice() <= remainingBudget) {
                selectedStocks.add(stock);
                remainingBudget -= stock.getPrice();
            }
        }

        return selectedStocks;
    }

    public static void AddStocks(List<Stock> stocks, int numberOfStocks) {
        for (int i = 0; i < numberOfStocks; i++) {
            // You may generate or fetch stock data here
            // For simplicity, adding a dummy stock
            stocks.add(new Stock(1 + i, 2 + i, 3 + i));
        }
    }

    public static void main(String[] args) {
        
        //Insert dummy data
        List<Stock> stocks = new ArrayList<>();
        AddStocks(stocks, 5000);

        double budget = 2500;
        long startTime = System.currentTimeMillis();
        List<Stock> selectedStocks = greedySelectStocks(stocks, budget);
        long endTime = System.currentTimeMillis();
        // Print the selected stocks
        for (Stock stock : selectedStocks) {
            System.out.printf("Price: $%.2f, Profitability: %.2f%n", stock.price, stock.pN / stock.price);
        }
        System.out.println("The total time in miliseconds is :" + (endTime - startTime));

    }
}
