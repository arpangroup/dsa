package design.buy_sell_stocks;

import java.util.TreeSet;

public class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        // price, amount -> price (Depends) | index (smallest) | amount(smallest First)

        // For a buy  -> look for Smallest sellPrice
        // For a sell -> look for Largest buyPrice
        TreeSet<Order> sellOrders = new TreeSet<>((o1, o2) -> { // Smallest price first
           if (o1.price == o2.price) return o1.id - o2.id;
           return o1.price - o2.price;
        });

        TreeSet<Order> buyOrders = new TreeSet<>((o1, o2) -> { // Largest price first
            if (o1.price == o2.price) return o1.id - o2.id;
            return o2.price - o1.price;
        });


        int id = 0;
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int type = order[2];

            if (type == 0) { // buy
                while (amount > 0 && sellOrders.size() > 0 && sellOrders.first().price <= price) {
                    Order sellOrder = sellOrders.pollFirst();
                    int req = Math.min(amount, sellOrder.amount);
                    amount -= req;
                    sellOrder.amount -= req;
                    if (sellOrder.amount > 0) sellOrders.add(new Order(sellOrder.id, sellOrder.price, sellOrder.amount));
                }
                if (amount > 0) buyOrders.add(new Order(id, price, amount));
            } else { // sell
                while (amount > 0 && buyOrders.size() > 0 && buyOrders.first().price >= price) {
                    Order buyOrder = buyOrders.pollFirst();
                    int req = Math.min(amount, buyOrder.amount);
                    amount -= req;
                    buyOrder.amount -= req;
                    if (buyOrder.amount > 0) buyOrders.add(new Order(buyOrder.id, buyOrder.price, buyOrder.amount));
                }
                if (amount > 0) sellOrders.add(new Order(id, price, amount));
            }
            id++;
        }

        int ans = 0;
        int mod = 10000__00007;
        for (Order order: buyOrders) ans = (ans + order.amount) % mod;
        for (Order order: sellOrders) ans = (ans + order.amount) % mod;
        return ans;
    }
}
