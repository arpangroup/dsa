package design.buy_sell_stocks;

public class Order {
    int id;
    int price;
    int amount;

    public Order(int id, int price, int amount) {
        this.id = id;
        this.price = price;
        this.amount = amount;
    }
}
