import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OptimalPlanning {
    public static List<Stock> optimalSelectStocks(List<Stock> stocks, double budget) {
        int numStocks = stocks.size();
        double[][] dp = new double[numStocks + 1][(int) budget + 1];

        for (int i = 1; i <= numStocks; i++) {
            for (int j = 0; j <= budget; j++) {
                dp[i][j] = dp[i - 1][j];
                if (stocks.get(i - 1).price <= j) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i - 1][(int) (j - stocks.get(i - 1).price)] + stocks.get(i - 1).pN);
                }
            }
        }

        List<Stock> selectedStocks = new ArrayList<>();
        int j = (int) budget;
        for (int i = numStocks; i > 0; i--) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedStocks.add(stocks.get(i - 1));
                j -= stocks.get(i - 1).price;
            }
        }
        Collections.reverse(selectedStocks);
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
        // Stock data
        List<Stock> stocks = new ArrayList<>();
        AddStocks(stocks, 10);

        double budget = 2500;
        long startTime = System.currentTimeMillis();
        List<Stock> selectedStocks = optimalSelectStocks(stocks, budget);
        long endTime = System.currentTimeMillis();
        System.out.println("Selected Stocks:");
        for (Stock stock : selectedStocks) {
            System.out.printf("Price: $%.2f, Profitability: %.2f%n", stock.price, stock.pN / stock.price);
        }
        System.out.println("The total time in miliseconds is :" + (endTime - startTime));
    }
}