import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static void main(String[] args) {
        // Stock data
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1000, 0.5, 2000));
        stocks.add(new Stock(150, 0.5, 300));
        stocks.add(new Stock(50, 0.6, 120));
        stocks.add(new Stock(80, 0.8, 180));
        stocks.add(new Stock(35, 0.3, 130));
        stocks.add(new Stock(30, 0.8, 70));
        stocks.add(new Stock(20, 0.3, 55));
        stocks.add(new Stock(15, 0.4, 50));
        stocks.add(new Stock(10, 0.5, 66));
        stocks.add(new Stock(5, 0.2, 20));

        double budget = 2500;
        List<Stock> selectedStocks = optimalSelectStocks(stocks, budget);
        System.out.println("Selected Stocks:");
        for (Stock stock : selectedStocks) {
            System.out.printf("Price: $%.2f, Profitability: %.2f%n", stock.price, stock.pN / stock.price);
        }
    }
}