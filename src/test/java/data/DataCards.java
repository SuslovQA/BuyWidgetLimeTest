package data;

public class DataCards {
    private String uid;
    private String balance;
    private String bonusBalance;
    private String dateExpiresBonus;
    private String category;
    private String email;

    public static final DataCards VALID_CARD_UID = new DataCards("AC11A1A3", "Взрослая", "test@lime-it.ru");
    public static final DataCards VALID_CHILD_CARD_UID = new DataCards("AC11A1A4", "Детская");
    public static final DataCards INVALID_CARD_UID = new DataCards("FAFEC888");
    public static final DataCards VALID_CARD_WITH_BALANCE = new DataCards("AC11A1A2", "100", "17", "29.04.2027", "Взрослая", null);
    public static final DataCards VALID_CARD_WITH_ZERO_BALANCE = new DataCards("AC11A1A1", "Взрослая");
    public static final DataCards VALID_PROMOCODE_WITH_TEN_PERCENT_DISCOUNT = new DataCards("discount10");

    public DataCards(String uid, String balance, String bonusBalance, String dateExpiresBonus, String category, String email) {
        this.uid = uid;
        this.balance = balance;
        this.bonusBalance = bonusBalance;
        this.dateExpiresBonus = dateExpiresBonus;
        this.category = category;
        this.email = email;
    }

    public DataCards(String uid) {
        uid = uid;
    }

    public DataCards(String uid, String category) {
        this.uid = uid;
        this.category = category;
    }

    public DataCards(String uid, String category, String email) {
        this.uid = uid;
        this.category = category;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getBalance() {
        return balance;
    }

    public String getBonusBalance() {
        return bonusBalance;
    }

    public String getDateExpiresBonus() {
        return dateExpiresBonus;
    }

    public String getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }
}