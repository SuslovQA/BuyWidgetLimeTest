package data;

public class AuthData {
    public static class Cards {
        static String validCardUid = "FAFEC877";
        static String invalidCardUid = "FAFEC888";
        static String validCardUidWithBalance = "FD12A1A1";
        static String validCardUidWithZeroBalance = "FD12A1A2";
        static String validPromoCode = "skidka10";

        public static String getValidCardUid() {
            return validCardUid;
        }

        public static String getValidCardUidWithBalance() {
            return validCardUidWithBalance;
        }

        public static String getValidCardUidWithZeroBalanceBalance() {
            return validCardUidWithZeroBalance;
        }

        public static String getInvalidCardUid() {
            return invalidCardUid;
        }

        public static String getValidPromoCodeForTenPercentDiscount() {
            return validPromoCode;
        }
    }

    public static class Emails {
        private static String validEmail = "see@lime-it.ru";

        public Emails(String validEmail) {
            this.validEmail = validEmail;
        }

        public static String getValidEmail() {
            return validEmail;
        }

        public void setEmail(String email) {
            this.validEmail = email;
        }
    }
}
