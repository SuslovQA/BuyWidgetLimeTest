import com.codeborne.selenide.Configuration;
import data.AuthData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import components.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class BuyWidgetTest {
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";

        mainPage = open("https://limepay.chudin.ru/buy/#/4501", MainPage.class);

//        Thread.sleep(15_000);
    }

    @Test
    @DisplayName("1.0 Авторизация по UID и добавление билета в корзину")
    void shouldAuthWithUidAndAddTicketInCart() {
//        mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid());
//        mainPage.tickets().addTicket(1);
//        mainPage.cart().purchaseOrder();
//        mainPage.orderPayment();
//        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());

        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addTicket(1);

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Аквапарк";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.1 Афторизация по UID и добавление одного билета из категории билетов")
    void shouldAuthWithUidAndAddTicketFromGroup() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addFirstTicketFromCategory();

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Билет 1";

        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    @DisplayName("1.2")
//    void shouldError() {
//        var expected = mainPage.auth()
//                .tickets().addTicket(1)
//                .message().getErrorMessage();
//        var actual = "Заполните код карты";
//
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    @DisplayName("1.3")
//    void shouldDisplaySuccessMessageAddTicketToCart() {
//        var expected = mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
//                .addTicket(1)
//                .message().getSuccessAddToCartMessage();
//        var actual = "Товар добавлен в корзину";
//
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    @DisplayName("1.4")
//    void should() {
//        System.out.println(mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
//                .addTicket(1)
//                .cart().getItemNameInCart());
//
//
//    }
}
