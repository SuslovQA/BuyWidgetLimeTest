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
    void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadStrategy = "eager";

        mainPage = open("https://limepay.chudin.ru/buy/#/4501", MainPage.class);

//        Thread.sleep(15_000);
    }

    @Test
    @DisplayName("1.0 Авторизация по UID  добавления билета в корзину")
    void shouldAuthAndAddTicketInCart() {
        mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets().addTicket(1);
        mainPage.cart().purchaseOrder();
        mainPage.orderPayment();
    }

    @Test
    @DisplayName("1.1")
    void shouldSome() {
       var actual = mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
                .addFirstTicketFromCategory().getItemNameInCart();
       var expected = "Биелет 1";

       Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.2")
    void shouldError() {
        var expected = mainPage.auth()
                .tickets().addTicket(1)
                .message().getErrorMessage();
        var actual = "Заполните код карты";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.3")
    void shouldDisplaySuccessMessageAddTicketToCart() {
        var expected = mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
                .addTicket(1)
                .message().getSuccessAddToCartMessage();
        var actual = "Товар добавлен в корзину";

        Assertions.assertEquals(expected, actual);
    }
}
