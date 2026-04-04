import data.Cards;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import components.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BuyWidgetTest {
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        mainPage = open("https://limepay.chudin.ru/buy/#/4334", MainPage.class);
    }

    @Test
    @DisplayName("1.0 Авторизация по UID  добавления билета в корзину")
    void shouldAuthAndAddTicketInCart() {
        mainPage.auth().authWithCardUid(Cards.getCardUid());
        mainPage.tickets().addTickets(0);
        mainPage.cart().purchaseOrder();
        mainPage.orderPayment();
    }
}
