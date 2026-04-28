package data;


import java.util.Collections;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DataHelper {
    public static final String TEXT_IN_MODAL_INFO_IN_TICKET_CARD = "Римский император Константин I Великий по достоинству оценил выгодное местоположение приморского Византия, расположенного на стыке Европы и Азии. Кроме того, на решение Константина повлияла неспокойная обстановка в самом Риме: недовольство знати и постоянные распри в борьбе за трон. Император хотел увенчать свою реформаторскую деятельность созданием нового административного центра огромной державы. Закладка города состоялась осенью 324 года, и Константин лично решил обозначить его границы.";

    public static String replaceUnicodeSpaceCharacterToSpace(String text) {
        return text.replace("\u00A0", " ");
    }

    public static String getRandomSum() {
        return String.valueOf(new Random().nextInt(6_000 - 1) + 1);
    }

    public static String calculateTicketsPrice(double... ticketsPrices) {
        double ticketsPrice = 0;
        for (double price : ticketsPrices) {
            ticketsPrice += price;
        }

        return String.valueOf(ticketsPrice);
    }

    public static String getMoneyBalance(String uid) {
        int cardId = 0;

        if (uid.equals(DataCards.VALID_CARD_UID)) {
            cardId = 2538453;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_BALANCE)) {
            cardId = 2576496;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_ZERO_BALANCE)) {
            cardId = 2576499;
        }

        List<Integer> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(147816))
                .param("uid", uid)
                .param("cardId", cardId)
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.amount");

        return String.valueOf(response.get(0) / 100);
    }

    public static String getBonusBalance(String uid) {
        int cardId = 0;

        if (uid.equals(DataCards.VALID_CARD_UID)) {
            cardId = 2538453;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_BALANCE)) {
            cardId = 2576496;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_ZERO_BALANCE)) {
            cardId = 2576499;
        }

        List<Integer> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(147816))
                .param("uid", uid)
                .param("cardId", cardId)
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.amount");

        return String.valueOf(response.get(1) / 100 + response.get(6) / 100);
    }

//    public static boolean isModalDisplayedAfterClick(SelenideElement element, Duration timeout) {
//        try {
//            // Пытаемся найти элемент и дождаться его видимости
//            element.shouldBe(Condition.visible, timeout);
//            return true; // Элемент появился и виден
//        } catch (AssertionError e) {
//            // Таймаут истек, элемент так и не появился
//            return false;
//        }
//    }
}
