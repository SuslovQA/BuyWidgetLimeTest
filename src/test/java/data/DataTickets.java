package data;


import java.util.ArrayList;
import java.util.List;

public class DataTickets {
    private int index;
    private String name;
    private double price;
//    public final DataTickets[] LIST_OF_TICKETS_IN_CATEGORY = {TICKET_1_IN_CATEGORY, TICKET_2_IN_CATEGORY, TICKET_3_IN_CATEGORY, TICKET_4_IN_CATEGORY, TICKET_5_IN_CATEGORY, TICKET_6_IN_CATEGORY, TICKET_7_IN_CATEGORY, TICKET_8_IN_CATEGORY, TICKET_9_IN_CATEGORY, TICKET_10_IN_CATEGORY};


    public static final DataTickets TICKET_1 = new DataTickets(1, "Аквапарк", 150);
    public static final DataTickets TICKET_2 = new DataTickets(2, "Хамам", 11);
    public static final DataTickets TICKET_3 = new DataTickets(3, "Бани", 1000);


    public static final DataTickets TICKET_1_IN_CATEGORY = new DataTickets(0, "Билет 1", 10.22);
    public static final DataTickets TICKET_2_IN_CATEGORY = new DataTickets(1, "Билет 2", 100);
    public static final DataTickets TICKET_3_IN_CATEGORY = new DataTickets(2, "Билет 3", 100);
    public static final DataTickets TICKET_4_IN_CATEGORY = new DataTickets(3, "Билет 4", 101);
    public static final DataTickets TICKET_5_IN_CATEGORY = new DataTickets(4, "Билет 5", 102);
    public static final DataTickets TICKET_6_IN_CATEGORY = new DataTickets(5, "Билет 6", 100);
    public static final DataTickets TICKET_7_IN_CATEGORY = new DataTickets(6, "Билет 7", 100);
    public static final DataTickets TICKET_8_IN_CATEGORY = new DataTickets(7, "Билет 8", 100);
    public static final DataTickets TICKET_9_IN_CATEGORY = new DataTickets(8, "Билет 9", 100);
    public static final DataTickets TICKET_10_IN_CATEGORY = new DataTickets(9, "Билет 10", 100);

    public DataTickets(int index, String name, double price) {
        this.index = index;
        this.name = name;
        this.price = price;
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
