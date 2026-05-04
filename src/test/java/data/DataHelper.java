package data;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class DataHelper {
    public static final String TEXT_IN_MODAL_INFO_IN_TICKET_CARD = "Римский император Константин I Великий по достоинству оценил выгодное местоположение приморского Византия, расположенного на стыке Европы и Азии. Кроме того, на решение Константина повлияла неспокойная обстановка в самом Риме: недовольство знати и постоянные распри в борьбе за трон. Император хотел увенчать свою реформаторскую деятельность созданием нового административного центра огромной державы. Закладка города состоялась осенью 324 года, и Константин лично решил обозначить его границы.";
    private static final int INSTALLATION_ID = 156920;

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

    public static int getCardId(String uid) {
        int result = 0;

        if (uid.equals(DataCards.VALID_CARD_UID.getUid())) {
            result = 2588402;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_BALANCE.getUid())) {
            result = 2588401;
        } else if (uid.equals(DataCards.VALID_CARD_WITH_ZERO_BALANCE.getUid())) {
            result = 2588400;
        }
        return result;
    }

    public static String getMoneyBalance(String uid) {
        List<Integer> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(INSTALLATION_ID))
                .param("uid", uid)
                .param("cardId", getCardId(uid))
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.amount");

        return String.valueOf(response.get(0) / 100);
    }

    public static String getBonusBalance(String uid) {
        List<Integer> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(INSTALLATION_ID))
                .param("uid", uid)
                .param("cardId", getCardId(uid))
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.amount");

        return String.valueOf(response.get(1) / 100 + response.get(2) / 100);
    }

    public static String getBonusDateExpires(String uid) {
        List<String> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(INSTALLATION_ID))
                .param("uid", uid)
                .param("cardId", getCardId(uid))
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.to");

        LocalDateTime dateTime = LocalDateTime.parse(response.get(2));

        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getExpiresBonusBalance(String uid) {
        List<Integer> response = given()

                .header("Authorization", "LimeToken 16be3b3a-f1a5-43d9-9264-47e2103782cf")
                .param("installationId", Collections.singleton(INSTALLATION_ID))
                .param("uid", uid)
                .param("cardId", getCardId(uid))
                .when()
                .get("https://limepay.chudin.ru/api/CashdeskServer/GetCardInfo")
                .then()
                .statusCode(200)
                .extract().path("rights.right.amount");

        return Integer.toString(response.get(2) / 100);
    }

    public static List<SelenideElement> getEvenElements(ElementsCollection elementsCollection) {
        List<SelenideElement> elements = IntStream.range(0, elementsCollection.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(elementsCollection::get)
                .collect(Collectors.toList());
        return elements;
    }

    public static List<SelenideElement> getOddElements(ElementsCollection elementsCollection) {
        List<SelenideElement> elements = IntStream.range(0, elementsCollection.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(elementsCollection::get)
                .collect(Collectors.toList());
        return elements;
    }
}
