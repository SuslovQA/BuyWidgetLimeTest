package data;

public class BaseTicket {
    private int index;
    protected String name;
    protected double price;

    public BaseTicket(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public BaseTicket(int index, String name, double price) {
        this.index = index;
        this.name = name;
        this.price = price;
    }

    public BaseTicket(String name) {
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
