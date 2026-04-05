import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import data.AuthData;
import io.github.bonigarcia.wdm.WebDriverManager;
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
        Configuration.timeout = 15_000;

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
        mainPage.auth()
                .tickets().addTicketsWithAuthWithEmail(0, AuthData.Emails.getEmail())
                .message().getErrorMessage().equals("Не найден покупатель по номеру карты");
    }
}
