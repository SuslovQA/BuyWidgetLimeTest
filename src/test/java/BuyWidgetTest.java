import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import data.AuthData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import components.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class BuyWidgetTest {
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

        mainPage = open("https://limepay.chudin.ru/buy/#/4334", MainPage.class);
    }

    @Test
    @DisplayName("1.0 Авторизация по UID  добавления билета в корзину")
    void shouldAuthAndAddTicketInCart() {
        mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets().addTickets(0);
        mainPage.cart().purchaseOrder();
        mainPage.orderPayment();
    }

    @Test
    @DisplayName("1.1")
    void shouldSome() {
        mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
                .addTickets(0)
                .purchaseOrder()
                .some();
    }

    @Test
    @DisplayName("1.2")
    void shouldError() {
        var expected = mainPage.auth()
                .tickets().addTickets(0)
                .message().getErrorMessage();
        var actual = "Заполните код карты";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.3")
    void shouldDisplaySuccessMessageAddTicketToCart() {
        var expected = mainPage.auth().authWithCardUid(AuthData.Cards.getCardUid())
                .addTickets(0)
                .message().getSuccessAddToCartMessage();
        var actual = "Товар добавлен в корзину";

        Assertions.assertEquals(expected, actual);
    }
}
