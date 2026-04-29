package data;

public class DataTickets extends BaseTicket{
    public static final DataTickets TICKET_1 = new DataTickets(1, "Билет без категории 1", 300);
    public static final DataTickets TICKET_2 = new DataTickets(2, "5 Билетов", 999.99);
    public static final DataTickets TICKET_3 = new DataTickets(3, "Пакет для витрины", 855);


    public static final DataTickets TICKET_1_IN_CATEGORY = new DataTickets(0, "Билет 1", 100.55);
    public static final DataTickets TICKET_2_IN_CATEGORY = new DataTickets(1, "Билет 2", 101);
    public static final DataTickets TICKET_3_IN_CATEGORY = new DataTickets(2, "Билет 3", 102);
    public static final DataTickets TICKET_4_IN_CATEGORY = new DataTickets(3, "Билет 4", 103);
    public static final DataTickets TICKET_5_IN_CATEGORY = new DataTickets(4, "Билет 5", 104);
    public static final DataTickets TICKET_6_IN_CATEGORY = new DataTickets(5, "Билет 6", 100);
    public static final DataTickets TICKET_7_IN_CATEGORY = new DataTickets(6, "Билет 7", 100);
    public static final DataTickets TICKET_8_IN_CATEGORY = new DataTickets(7, "Билет 8", 100);
    public static final DataTickets TICKET_9_IN_CATEGORY = new DataTickets(8, "Билет 9", 100);
    public static final DataTickets TICKET_10_IN_CATEGORY = new DataTickets(9, "Билет 10", 100);

    public DataTickets(int index, String name, double price) {
        super(index, name, price);
    }
}
