package data;

public class DataEvents {
    private int index;
    private String name;
    private double price;
    private double childSeatTypePrice;
    private double adultSeatTypePrice;
    private String time;

    public static final DataEvents EVENT_1 = new DataEvents(0, "Веб витр", 500);
    public static final DataEvents EVENT_2 = new DataEvents(1, "Бассейн", 200);
    public static final DataEvents EVENT_3 = new DataEvents(2, "Каток", 50, 100);
    public static final DataEvents TICKET_1_EVENT_1 = new DataEvents(0, null, 500, "13:00 - 14:00");

    public DataEvents(int index, String name, double price) {
        this.index = index;
        this.name = name;
        this.price = price;
    }

    public DataEvents(int index, String name, double childSeatTypePrice, double adultSeatTypePrice) {
        this.index = index;
        this.name = name;
        this.childSeatTypePrice = childSeatTypePrice;
        this.adultSeatTypePrice = adultSeatTypePrice;
    }

    public DataEvents(int index, String name, double price, String time) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.time = time;
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

    public double getChildSeatTypePrice() {
        return childSeatTypePrice;
    }

    public double getAdultSeatTypePrice() {
        return adultSeatTypePrice;
    }
}
