package design.currency_exchange;

import java.util.*;

public class Edge {
    public String toCurrency;
    public double rate;

    public Edge(String toCurrency, double rate) {
        this.toCurrency = toCurrency;
        this.rate = rate;
    }
}
