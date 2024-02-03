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

    public static void main(String[] args) {
        //Insert dummy data
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(50, 0.6, 120));
        stocks.add(new Stock(30, 0.8, 70));
        stocks.add(new Stock(15, 0.4, 50));
        stocks.add(new Stock(10, 0.5, 66));

        double budget = 2500;
        List<Stock> selectedStocks = greedySelectStocks(stocks, budget);

        // Print the selected stocks
        for (Stock stock : selectedStocks) {
            System.out.println("Selected Stock: " + stock + ", Profitability: " + (stock.getpProb() / stock.getPrice()));
        }
    }
}
