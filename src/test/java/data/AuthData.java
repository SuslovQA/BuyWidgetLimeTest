package data;

public class AuthData {
    public static class Cards {
        private static String cardUid = "FAFEC877";
        private static String invalidCardUid = "FAFEC888";

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

    public static class Emails {
        private static String email = "see@lime-it.ru";

        public Emails(String email) {
            this.email = email;
        }

        public static String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
