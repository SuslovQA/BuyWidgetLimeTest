import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.AuthData;
import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import components.MainPage;


import static com.codeborne.selenide.Selenide.*;

public class BuyWidgetTest {
    MainPage mainPage;

    DataHelper.Ticket ticket1 = new DataHelper.Ticket(1, "Аквапарк", 150);
    DataHelper.Ticket ticket2 = new DataHelper.Ticket(2, "Хамам", 11);
    DataHelper.Ticket ticket3 = new DataHelper.Ticket(3, "Бани", 1000);
    DataHelper.Ticket ticketFromCategory1 = new DataHelper.Ticket(0, "Билет 1", 10.22);
    DataHelper.Ticket getTicketFromCategory2 = new DataHelper.Ticket(2, "Билет 2", 100);
    DataHelper.Ticket getGetTicketFromCategory3 = new DataHelper.Ticket(3, "Билет 3", 100);

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 30_000;
        Configuration.pageLoadStrategy = "eager";

        mainPage = open("https://limepay.chudin.ru/buy/#/4501", MainPage.class);
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("1.0 Успешное добавление одного билета в корзину с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketInCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addTicketWithClickOnAuthConfirm(ticket1.getIndex());

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Аквапарк";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.1 Успешное добавление одного билета из категории билетов с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketFromGroup() throws InterruptedException {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addFirstTicketFromCategory();

        Thread.sleep(2000);

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Билет 1";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.2 Отображение сообщения 'Товар добавлен в корзину' при успешном добавлении билета в корзину со скролом и авторизацией по UID")
    void shouldDisplaySuccessMessageAddTicketToCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());

        var actual = mainPage.tickets.clickEnabledNavButtonInSwiper()
                .addTicketWithClickOnAuthConfirm(ticket3.getIndex())
                .getSuccessAddToCartMessage();
        var expected = "Товар добавлен в корзину";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.3 Успешное добавление двух одинаковых билетов в корзину с авторизацией по UID")
    void shouldAddTwoEqualsTicketsToCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addSomeEqualsTickets(ticket2.getIndex(), 2);

        var actual = mainPage.cart.getItemAmountInCart(0);
        var expected = 2;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.4 Успешное добавление трех разных билетов в корзину с авторизацией по UID")
    void shouldAddSomeDifferentTicketsToCart() throws InterruptedException {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addTicketWithClickOnAuthConfirm(ticket1.getIndex())
                .addTicket(ticket2.getIndex())
                .clickEnabledNavButtonInSwiper()
                .addTicket(ticket3.getIndex());

        Thread.sleep(1000);

        var actual = mainPage.cart.getListOfItemsNamesInCart();

        Assertions.assertTrue(actual.contains(ticket1.getName()));
        Assertions.assertTrue(actual.contains(ticket2.getName()));
        Assertions.assertTrue(actual.contains(ticket3.getName()));


    }

    @Test
    @DisplayName("1.5 Успешная покупка билета виртуальным процессингом")
    void shouldBuyTicketByVirtualProcessing() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getCardUid());
        mainPage.tickets.addTicketFromCategory(0);
        mainPage.tickets.addTicketFromCategory(1);
        mainPage.tickets.addTicketFromCategory(2);
        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton();
        mainPage.orderPayment.returnToTheShopFromVirtualPayment();

        var actual = mainPage.orderPayment.getSuccessPaymentModalHeader();
        var expected = "Оплата прошла успешно!";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.1 Отображение ошибки 'Заполните код карты' при попытке добавления билета и отправке пустой формы авторизации")
    void shouldDisplayErrorBeforeAddingTicketInCartWithEmptyAuthField() {
        mainPage.tickets.addTicket(2);

        var actual = mainPage.auth.clickConfirmAuthButtonInModal().getErrorMessageEmptyAuthField();
        var expected = "Заполните код карты";

        Assertions.assertEquals(expected, actual);
    }
}
