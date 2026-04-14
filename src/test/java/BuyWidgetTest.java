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
    DataHelper.Ticket ticketFromCategory2 = new DataHelper.Ticket(1, "Билет 2", 100);
    DataHelper.Ticket ticketFromCategory3 = new DataHelper.Ticket(2, "Билет 3", 100);
    DataHelper.Ticket ticketFromCategory4 = new DataHelper.Ticket(3, "Билет 4", 101);
    DataHelper.Ticket ticketFromCategory5 = new DataHelper.Ticket(4, "Билет 5", 102);
    DataHelper.Ticket ticketFromCategory6 = new DataHelper.Ticket(5, "Билет 6", 100);
    DataHelper.Ticket ticketFromCategory7 = new DataHelper.Ticket(6, "Билет 7", 100);
    DataHelper.Ticket ticketFromCategory8 = new DataHelper.Ticket(7, "Билет 8", 100);
    DataHelper.Ticket ticketFromCategory9 = new DataHelper.Ticket(8, "Билет 9", 100);
    DataHelper.Ticket ticketFromCategory10 = new DataHelper.Ticket(9, "Билет 10", 100);

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
    @DisplayName("0.1 Проверка созданных элементов на странице (лого, билеты, события, счета)")
    void shouldCheckExistingElementsInMainPage() {
        mainPage.checkElementsInMainPage();
    }

    @Test
    @DisplayName("1.0 Успешное добавление одного билета в корзину с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketInCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addTicketWithClickOnAuthConfirm(ticket1.getIndex());

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Аквапарк";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.1 Успешное добавление одного билета из категории билетов с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketFromGroup() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addFirstTicketFromCategory();

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Билет 1";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.2 Отображение сообщения 'Товар добавлен в корзину' при успешном добавлении билета в корзину со скролом и авторизацией по UID")
    void shouldDisplaySuccessMessageAddTicketToCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());

        var actual = mainPage.tickets.clickEnabledNavButtonInSwiper()
                .addTicketWithClickOnAuthConfirm(ticket3.getIndex())
                .getSuccessAddToCartMessage();
        var expected = "Товар добавлен в корзину";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.3 Успешное добавление двух одинаковых билетов в корзину с авторизацией по UID")
    void shouldAddTwoEqualsTicketsToCart() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addSomeEqualsTickets(ticket2.getIndex(), 2);

        var actual = mainPage.cart.getItemAmountInCart(0);
        var expected = 2;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.4 Успешное добавление трех разных билетов в корзину с авторизацией по UID")
    void shouldAddSomeDifferentTicketsToCart() throws InterruptedException {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
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
    @DisplayName("1.5 Успешная покупка билета виртуальным процессингом c проверкой суммы в окне успешной оплаты")
    void shouldBuyTicketByVirtualProcessing() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addTicketFromCategory(0);
        mainPage.tickets.addTicketFromCategory(1);
        mainPage.tickets.addTicketFromCategory(2);
        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton();
        mainPage.orderPayment.returnToTheShopFromVirtualPayment();

        var actual = mainPage.orderPayment.getTotalSumInSuccessPaymentModal();
        var expected = ticketFromCategory1.getPrice() + ticketFromCategory2.getPrice() + ticketFromCategory3.getPrice();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.6 Успешная покупка билета виртуальным процессингом c проверкой почты в окне успешной оплаты")
    void shouldBuyTicketByVirtualProcessingWithCheckingEmail() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addTicketWithClickOnAuthConfirm(ticket1.getIndex());
        mainPage.tickets.addTicket(ticket2.getIndex());
        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton();
        mainPage.orderPayment.returnToTheShopFromVirtualPayment();
        mainPage.orderPayment.checkElementsInSuccessPaymentModal();

        var actual = mainPage.orderPayment.getTextWithEmailInSuccessPaymentModal();
        var expected = "Ваши билеты отправлены на: test@mail.ru.";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.0 Успешная авторизация по UID")
    void shouldSuccessAuthWithUid() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());

        var actual = mainPage.auth.getValueInAuthButton();
        var expected = AuthData.Cards.getValidCardUid();

        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    @DisplayName("2.1 Проверка денежного баланса в окне авторизации")
    void shouldSuccessCheckingMoneyBalance() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkMoneyBalance();
        var expected = 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.2 Проверка нулевого баланса в окне авторизации")
    void shouldSuccessCheckingZeroBalance() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithZeroBalanceBalance());

        var actual = mainPage.auth.checkMoneyBalance();
        var expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.3 Проверка баланса бонусов в окне авторизации")
    void shouldSuccessCheckingBonusBalance() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkBonusBalance();
        var expected = 16;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.4 Проверка баланса сгораемых бонусов в окне авторизации")
    void shouldSuccessCheckingExpirationBonusBalance() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkExpirationBonusBalance();
        var expected = 11;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.5 Проверка даты сгораемых бонусов в окне авторизации")
    void shouldSuccessCheckingDateExpirationBonus() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkExpirationDateBonus();
        var expected = "09.04.2027";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.6 Переход к поплнению счета из окна авторизации после успешной авторизации по UID")
    void shouldSuccessRedirectingToRefillAccountFromAuthModal() {
       mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithZeroBalanceBalance());
       mainPage.auth.getRefillAccountFromAuthModal();

       var actual = mainPage.refillAccount.getRefillAccountHeader();
       var expected = "Счет";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.7 Выход из аккаунта после успешной авторизации по UID")
    void shouldSuccessLogoutFromAuthModal() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithZeroBalanceBalance());
        mainPage.auth.logout();

        var actual = mainPage.auth.getValueInAuthButton();
        var expected = "Уже есть карта?";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.8 Отображение ошибки 'Не найден покупатель по номеру карты' при авторизации по UID невалидной карты")
    void shouldDisplayErrorOnAuthWithInvalidCard() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getInvalidCardUid());

        var actual = mainPage.auth.getErrorMessageWithInvalidAuthData();
        var expected = "Не найден покупатель по номеру карты";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.9 Проверка денежного баланса на кнопке авторизации после успешной авторизации")
    void shouldCheckMoneyBalanceInAuthButtonAfterSuccessAuth() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkMoneyBalanceInAuthButton();
        var expected = 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.9.1 Проверка баланса бонусов на кнопке авторизации после успешной авторизации")
    void shouldCheckBonusBalanceInAuthButtonAfterSuccessAuth() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUidWithBalance());

        var actual = mainPage.auth.checkBonusBalanceInAuthButton();
        var expected = 16;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3.0 Применение скидки в корзине с одним билетом")
    void shouldApplyingDiscountInCartWithOneTicket() {
        mainPage.auth.authWithCardUid(AuthData.Cards.getValidCardUid());
        mainPage.tickets.addTicketWithClickOnAuthConfirm(ticket1.getIndex());
        mainPage.cart.applyDiscount(AuthData.Cards.getValidPromoCodeForTenPercentDiscount());

        Assertions.assertTrue(mainPage.cart.checkApplyingTenPercentDiscount());
    }

//    @Test
//    @DisplayName("2.9 Отображение ошибки 'Заполните код карты' при попытке добавления билета и отправке пустой формы авторизации")
//    void shouldDisplayErrorBeforeAddingTicketInCartWithEmptyAuthField() {
////        mainPage.tickets.addTicket(2);
//
//        mainPage.auth.checkDisabledConfirmAuthButtonWithEmptyAuthField();
//
//    }
}
