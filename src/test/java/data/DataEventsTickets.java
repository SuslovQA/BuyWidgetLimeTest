package data;

public class DataEventsTickets extends BaseTicket {
//    private String secondName;
//    private double childOrFirstSeatTypePrice;
//    private double adultOrSecondSeatTypePrice;
//    private String time;

    public static DataEventsTickets EVENT_1_TICKET = new DataEventsTickets("Взрослые для витрины", 100);
//    public static DataEventsTickets EVENT_1_TICKET_11_14 = new DataEventsTickets("Взрослые для витрины", 100, "11:00 - 14:00");
//    public static DataEventsTickets EVENT_1_TICKET_14_17 = new DataEventsTickets("Взрослые для витрины", 100, "14:00 - 17:00");
//    public static DataEventsTickets EVENT_1_TICKET_17_20 = new DataEventsTickets("Взрослые для витрины", 100, "17:00 - 20:00");

    public static DataEventsTickets EVENT_2_TICKET_CHILD = new DataEventsTickets("Детские",  400);
    public static DataEventsTickets EVENT_2_TICKET_ADULT = new DataEventsTickets("Взрослые для витрины",500);
//    public static DataEventsTickets EVENT_2_TICKET_17_20 = new DataEventsTickets("Детские", "Взрослые для витрины", 400, 500, "17:00 - 20:00");

    public static DataEventsTickets EVENT_3_TICKET = new DataEventsTickets("Стандарт", 350);
//    public static DataEventsTickets EVENT_3_TICKET_10_12 = new DataEventsTickets("Стандарт", 350, "10:00 - 12:00");
//    public static DataEventsTickets EVENT_3_TICKET_12_14 = new DataEventsTickets("Стандарт", 350, "12:00 - 14:00");

    public static DataEventsTickets EVENT_4_TICKET_TYPE_1 = new DataEventsTickets("Тип 1 для группы",150);
    public static DataEventsTickets EVENT_4_TICKET_TYPE2 = new DataEventsTickets("Тип 2 для группы", 250);
//    public static DataEventsTickets EVENT_4_TICKET_12_14 = new DataEventsTickets("Тип 1 для группы", "Тип 2 для группы",150, 250,"12:00 - 14:00");
//    public static DataEventsTickets EVENT_4_TICKET_14_16 = new DataEventsTickets("Тип 1 для группы","Тип 2 для группы",150, 250,"14:00 - 16:00");
//    public static DataEventsTickets EVENT_4_TICKET_16_18 = new DataEventsTickets("Тип 1 для группы", "Тип 2 для группы",150, 250,"16:00 - 18:00");
//    public static DataEventsTickets EVENT_4_TICKET_18_20 = new DataEventsTickets("Тип 1 для группы", "Тип 2 для группы",150, 250, "18:00 - 20:00");

    public DataEventsTickets(String name, double price) {
        super(name, price);
    }

//    public DataEventsTickets(String name, String secondName, double childOrFirstSeatTypePrice, double adultOrSecondSeatTypePrice) {
//        super(name);
//        this.secondName = secondName;
//        this.childOrFirstSeatTypePrice = childOrFirstSeatTypePrice;
//        this.adultOrSecondSeatTypePrice = adultOrSecondSeatTypePrice;
//    }
//
//    public double getChildOrFirstSeatTypePrice() {
//        return childOrFirstSeatTypePrice;
//    }
//
//    public double getAdultOrSecondSeatTypePrice() {
//        return adultOrSecondSeatTypePrice;
//    }
//
//    public String getTime() {
//        return time;
//    }
}
