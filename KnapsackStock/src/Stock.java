public class Stock {
    private double price;
    private double pProb;
    private double pN;
    
    /*
        price p_s (double between $1 and $100) 
        a probability that it will increase in price p_prob (double between 0 and 1  of course)
        a new possible price. p_n (double between $1 and $200)
    
    Number of stocks will determine the size of the problem and the time it will take to run so you can experiment with larger numbers until 1000 stocks.
    
    Budget is $2500.
    */
    public Stock(double price, double pProb, double pN) {
        this.setPrice(price);
        this.setpProb(pProb);
        this.setpN(pN);
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getpProb() {
        return pProb;
    }
    
    public void setpProb(double pProb) {
        this.pProb = pProb;
    }
    
    public double getpN() {
        return pN;
    }
    
    public void setpN(double pN) {
        this.pN = pN;
    }
    }
    