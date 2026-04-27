package data;


import java.math.MathContext;
import java.util.Random;

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
