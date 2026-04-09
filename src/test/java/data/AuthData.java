package data;

public class AuthData {
    public static class Cards {
        private static String validCardUid = "FAFEC877";
        private static String invalidCardUid = "FAFEC888";
        private static String validCardUidWithBalance = "FD12A1A1";
        private static String validCardUidWithZeroBalance = "FD12A1A2";

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
