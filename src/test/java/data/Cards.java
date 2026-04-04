package data;

public class Cards {
    private static String cardUid = "FAFEC877";

    public Cards(String cardUid) {
        this.cardUid = cardUid;
    }

    public static String getCardUid() {
        return cardUid;
    }

    public void setCardUid(String cardUid) {
        this.cardUid = cardUid;
    }
}
