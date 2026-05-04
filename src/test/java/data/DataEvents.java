package data;


import java.util.HashMap;
import java.util.Map;

public class DataEvents {
    private int index;
    private String name;
    private double price;
    private double childOrFirstSeatTypePrice;
    private double adultOrSecondSeatTypePrice;

    public static final DataEvents EVENT_1 = new DataEvents(0, "Мероприятие 1", 100);
    public static final DataEvents EVENT_2 = new DataEvents(1, "Мероприятие 2", 400, 500);
    public static final DataEvents EVENT_3 = new DataEvents(2, "Мероприятие 3", 350);
    public static final DataEvents EVENT_4 = new DataEvents(2, "Мероприятие 4", 150, 250);

    public DataEvents(int index, String name, double price) {
        this.index = index;
        this.name = name;
        this.price = price;
    }

    public DataEvents(int index, String name, double childOrFirstSeatTypePrice, double adultOrSecondSeatTypePrice) {
        this.index = index;
        this.name = name;
        this.childOrFirstSeatTypePrice = childOrFirstSeatTypePrice;
        this.adultOrSecondSeatTypePrice = adultOrSecondSeatTypePrice;
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

    public double getChildOrFirstSeatTypePrice() {
        return childOrFirstSeatTypePrice;
    }

    public double getAdultOrSecondSeatTypePrice() {
        return adultOrSecondSeatTypePrice;
    }
}
