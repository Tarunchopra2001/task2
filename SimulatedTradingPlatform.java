import java.util.*;

// This is a placeholder for a real market data API
class Market {
    public static double getPrice(String symbol) {
        // Replace with actual API call to get real-time or historical data
        return 100.00;
    }
}

// Trading engine to handle buy and sell orders
class TradingEngine {
    public static void executeTrade(String symbol, int quantity, TradeType tradeType) {
        double price = Market.getPrice(symbol);
        
        // Simplified trade execution logic
        if (tradeType == TradeType.BUY) {
            double totalCost = price * quantity;
            System.out.printf("Bought \n", quantity, symbol, price, totalCost);
        } else if (tradeType == TradeType.SELL) {
            double totalSale = price * quantity;
            System.out.printf("Sold\n", quantity, symbol, price, totalSale);
        } else {
            System.out.println("Invalid trade type");
        }
    }
}

// Portfolio tracking
class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    
    public void addTrade(String symbol, int quantity, TradeType tradeType) {
        if (tradeType == TradeType.BUY) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        } else if (tradeType == TradeType.SELL) {
            int newQuantity = holdings.getOrDefault(symbol, 0) - quantity;
            if (newQuantity <= 0) {
                holdings.remove(symbol);
            } else {
                holdings.put(symbol, newQuantity);
            }
        }
    }
    
    public double getPortfolioValue() {
        double totalValue = 0;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double price = Market.getPrice(symbol);
            totalValue += price * quantity;
        }
        return totalValue;
    }
}

enum TradeType {
    BUY, SELL
}

// Example usage
public class SimulatedTradingPlatform {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        TradingEngine.executeTrade("AAPL", 10, TradeType.BUY);
        portfolio.addTrade("AAPL", 10, TradeType.BUY);
        System.out.printf("Portfolio value: %.2f\n", portfolio.getPortfolioValue());
    }
}
