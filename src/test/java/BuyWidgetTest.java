import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.DataCards;
import data.DataEmails;
import data.DataHelper;
import data.DataTickets;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import components.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class BuyWidgetTest {
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
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
    @DisplayName("0.2 Проверка, что в карточкахбез изображения добавлены дефолтные изображения")
    void shouldCheckDefaultPicturesInCards() {
        Assertions.assertTrue(mainPage.tickets.checkDefaultImgInCards());
    }

    @Test
    @DisplayName("1.0 Успешное добавление одного билета в корзину с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketInCart() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_1.getIndex());

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Аквапарк";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.1 Успешное добавление одного билета из категории билетов с авторизацией по UID")
    void shouldAuthWithUidAndAddTicketFromGroup() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addFirstTicketFromCategory();

        var actual = mainPage.cart.getItemNameInCart();
        var expected = "Билет 1";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.2 Отображение сообщения 'Товар добавлен в корзину' при успешном добавлении билета в корзину со скролом и авторизацией по UID")
    void shouldDisplaySuccessMessageAddTicketToCart() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);

        var actual = mainPage.tickets.clickEnabledNavButtonInSwiper()
                .addTicketWithClickOnAuthConfirm(DataTickets.TICKET_3.getIndex())
                .getSuccessAddToCartMessage();
        var expected = "Товар добавлен в корзину";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.3 Успешное добавление двух одинаковых билетов в корзину с авторизацией по UID")
    void shouldAddTwoEqualsTicketsToCart() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addSomeEqualsTickets(DataTickets.TICKET_2.getIndex(), 2);

        var actual = mainPage.cart.getItemAmountInCart(0);
        var expected = 2;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.4 Успешное добавление трех разных билетов в корзину с авторизацией по UID")
    void shouldAddSomeDifferentTicketsToCart() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_1.getIndex())
                .addTicket(DataTickets.TICKET_2.getIndex())
                .clickEnabledNavButtonInSwiper()
                .addTicket(DataTickets.TICKET_3.getIndex());

        var actual = mainPage.cart.getListOfItemsNamesInCart();

        Assertions.assertTrue(actual.contains(DataTickets.TICKET_1.getName()));
        Assertions.assertTrue(actual.contains(DataTickets.TICKET_2.getName()));
        Assertions.assertTrue(actual.contains(DataTickets.TICKET_3.getName()));
    }

    @Test
    @DisplayName("1.5 Успешная покупка билета виртуальным процессингом c проверкой суммы в окне успешной оплаты")
    void shouldBuyTicketByVirtualProcessing() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketFromCategoryByIndex(0);
        mainPage.tickets.addTicketFromCategoryByIndex(1);
        mainPage.tickets.addTicketFromCategoryByIndex(2);
        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton();
        mainPage.orderPayment.returnToTheShopFromVirtualPayment();

        var actual = mainPage.orderPayment.getTotalSumInSuccessPaymentModal();
        var expected = DataTickets.TICKET_1_IN_CATEGORY.getPrice() + DataTickets.TICKET_2_IN_CATEGORY.getPrice() + DataTickets.TICKET_3_IN_CATEGORY.getPrice();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.6 Успешная покупка билета виртуальным процессингом c проверкой почты в окне успешной оплаты")
    void shouldBuyTicketByVirtualProcessingWithCheckingEmail() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_1.getIndex());
        mainPage.tickets.addTicket(DataTickets.TICKET_2.getIndex());
        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton();
        mainPage.orderPayment.returnToTheShopFromVirtualPayment();
        mainPage.orderPayment.checkElementsInSuccessPaymentModal();

        var actual = mainPage.orderPayment.getTextWithEmailInSuccessPaymentModal();
        var expected = "Ваши билеты отправлены на: " + DataEmails.VALID_CARD_EMAIL + ".";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.7 Успешная очистка корзины после добавления двух билетов")
    void shouldRemoveTicketsFromCart() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_2.getIndex())
                .addTicket(DataTickets.TICKET_1.getIndex());
        mainPage.cart.clearCart();

        Assertions.assertTrue(mainPage.cart.checkRemovingCart());
    }

    @Test
    @DisplayName("1.8 Удаление одного билета из корзины с проверкой по количеству")
    void shouldRemoveOneTicketFromCartWithCountCheck() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addSomeEqualsTickets(DataTickets.TICKET_3_IN_CATEGORY.getIndex(), 3);
        mainPage.cart.removeOneTicketFromCartByIndex(0);

        var actual = mainPage.cart.getItemAmountInCart(0);
        var expected = 2;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1.9 Удаление одного билета из корзины с проверкой по названию")
    void shouldRemoveOneTicketFromCartWithNameCheck() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_2.getIndex());
        mainPage.tickets.addTicket(DataTickets.TICKET_1.getIndex());
        mainPage.cart.removeOneTicketFromCartByIndex(0);

        var result = mainPage.cart.checkRemovingTicketFromCartByTicketName(DataTickets.TICKET_2.getName());

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("1.10 Открытие и закрытие категории билетов без авторизации")
    void shouldCheckOpenAndCloseTicketCategory() {
        Assertions.assertTrue(mainPage.tickets.checkCloseCategoryTickets());
    }

    @Test
    @DisplayName("1.11 Открытие всех билетов после нажатия кнопки 'Все билеты'")
    void shouldCheckOpeningAllTickets() {
        Assertions.assertTrue(mainPage.tickets.checkTicketAfterClickOnAll());
    }

    @Test
    @DisplayName("1.12 Возврат на главную страницу после открытия всех билетов по нажатию на кнопку 'home-back'(стрелка)")
    void shouldReturnBackToMainPageFromAllTicketsAfterClickHomeBackButton() {
        mainPage.tickets.clickOnAllTicketsButton();

        Assertions.assertTrue(mainPage.tickets.checkReturningToMainPageFromAllTicketsAfterClickOnHomeBackButton());
    }

    @Test
    @DisplayName("1.13 Возврат на главную страницу после открытия всех билетов по нажатию на лого в хэдере")
    void shouldReturnBackToMainPageFromAllTicketsAfterClickLogoInHeader() {
        mainPage.tickets.clickOnAllTicketsButton();

        Assertions.assertTrue(mainPage.tickets.checkReturningToMainPageFromAllTicketsAfterClickOnLogoInHeader());
    }

    @Test
    @DisplayName("2.0 Успешная авторизация по UID")
    void shouldSuccessAuthWithUid() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);

        var actual = mainPage.auth.getValueInAuthButton();
        var expected = DataCards.VALID_CARD_UID;

        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    @DisplayName("2.1 Проверка денежного баланса в окне авторизации")
    void shouldSuccessCheckingMoneyBalance() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.checkMoneyBalance();
        var expected = 100;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.2 Проверка нулевого баланса в окне авторизации")
    void shouldSuccessCheckingZeroBalance() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_ZERO_BALANCE);

        var actual = mainPage.auth.checkMoneyBalance();
        var expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.3 Проверка баланса бонусов в окне авторизации")
    void shouldSuccessCheckingBonusBalance() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.checkBonusBalance();
        var expected = 16;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.4 Проверка баланса сгораемых бонусов в окне авторизации")
    void shouldSuccessCheckingExpirationBonusBalance() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.checkExpirationBonusBalance();
        var expected = 11;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.5 Проверка даты сгораемых бонусов в окне авторизации")
    void shouldSuccessCheckingDateExpirationBonus() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.checkExpirationDateBonus();
        var expected = DataCards.DATE_EXPIRES_BONUS;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.6 Переход к поплнению счета из окна авторизации после успешной авторизации по UID")
    void shouldSuccessRedirectingToRefillAccountFromAuthModal() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_ZERO_BALANCE);
        mainPage.auth.getRefillAccountFromAuthModal();

        var actual = mainPage.refillAccount.getRefillAccountHeader();
        var expected = "Счет";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.7 Выход из аккаунта после успешной авторизации по UID")
    void shouldSuccessLogoutFromAuthModal() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_ZERO_BALANCE);
        mainPage.auth.logout();

        var actual = mainPage.auth.getValueInAuthButton();
        var expected = "Уже есть карта?";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.8 Отображение ошибки 'Не найден покупатель по номеру карты' при попытке авторизации по UID невалидной карты")
    void shouldDisplayErrorOnAuthWithInvalidCard() {
        mainPage.auth.authWithCardUid(DataCards.INVALID_CARD_UID);

        var actual = mainPage.auth.getErrorMessageWithInvalidAuthData();
        var expected = "Не найден покупатель по номеру карты";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.9 Проверка денежного баланса на кнопке авторизации после успешной авторизации")
    void shouldCheckMoneyBalanceInAuthButtonAfterSuccessAuth() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.getMoneyBalanceInAuthButton();
        var expected = "100";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.10 Проверка баланса бонусов на кнопке авторизации после успешной авторизации")
    void shouldCheckBonusBalanceInAuthButtonAfterSuccessAuth() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.checkBonusBalanceInAuthButton();
        var expected = "16";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.11 Проверкакнопка наличия атрибута 'disabled' у кнопки 'Продолжить' в окне авторизации если не введен UID")
    void shouldCheckConfirmAuthButtonHaveAttributeIsDisabledWithEmptyAuthField() {
        Assertions.assertTrue(mainPage.auth.checkDisabledConfirmAuthButtonWithEmptyAuthField());
    }

    @Test
    @DisplayName("2.12 Проверка атрибута 'source' и отображения иконки в окне авторизации")
    void shouldCheckSourceImgInAuthContainer() {
        Assertions.assertTrue(mainPage.auth.checkSourceOfImgInAuthButton());
    }

    @Test
    @DisplayName("2.13 Успешная авторизация с другим UID после авторизации и выхода из аккаунта")
    void shouldSuccessAuthWithOtherUidAfterLogout() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.auth.logout();
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_WITH_BALANCE);

        var actual = mainPage.auth.getValueInAuthButton();
        var expected = DataCards.VALID_CARD_WITH_BALANCE;

        Assertions.assertTrue(actual.contains(expected));
    }



    @Test
    @DisplayName("3.0 Проверка количества событий")
    void shouldCheckCountOfEvents() {
        Assertions.assertTrue(mainPage.events.checkCountOfEvents(3));
    }

    @Test
    @DisplayName("3.1 Проверка текущей даты в календаре в компоненте 'События'")
    void shouldCheckDateInDatePicker() {
        Assertions.assertTrue(mainPage.events.checkNowDate());
    }

    @Test
    @DisplayName("4.0 Проверка добавления сумму в корзину по нажатию кнопки 'Выбора суммы'")
    void shouldCheckSumInCartAfterRefillFromSumButtons() {
        mainPage.auth.doubleAuthWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.refillAccount.AddSumFromButtonsSumsByIndex(0);

        var actual= mainPage.refillAccount.getAddedSumInCart();
        var expected = mainPage.refillAccount.getAmountSumsList().get(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("4.1 Проверка добавления суммы в корзину при переходе к пополнению из окна авторизации")
    void shouldCheckSumInCartAfterTransferToRefillFromAuthModal() {
        mainPage.auth.doubleAuthWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.auth.getRefillAccountFromAuthModal();
        mainPage.refillAccount.AddSumFromButtonsSumsByIndex(3);

        var expected = mainPage.refillAccount.getAddedSumInCart();
        var actual = DataHelper.replaceUnicodeSpaceCharacterToSpace(mainPage.refillAccount.getAmountSumsList().get(3));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("4.2 Проверка добавления суммы в корзину при вводе в поле ввода суммы")
    void shouldCheckSumInCartRefillFromInputWithRandomSum() {
        String userSum = DataHelper.getRandomSum();

        mainPage.auth.doubleAuthWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.refillAccount.addSumFromInput(userSum);

        var actual = mainPage.refillAccount.getAddedSumInCartWithTrim();

        Assertions.assertEquals(userSum, actual);
    }

    @Test
    @DisplayName("4.3 Проверка изменения баланса на кнопке авторизации и окне авторизации после пополнения")
    void shouldCheckChangeBalanceInAuthButtonAndAuthModalAfterRefilling() {
        mainPage.auth.doubleAuthWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.refillAccount.AddSumFromButtonsSumsByIndex(1);

        var currentBalance = mainPage.auth.getMoneyBalanceInAuthButton();
        var sumInCart = mainPage.refillAccount.getAddedSumInCart();

        mainPage.cart.makeOrder();
        mainPage.orderPayment.clickVirtualProcessingButton().returnToTheShopFromVirtualPaymentAndCloseStatusModal();

        var actual = mainPage.auth.getMoneyBalanceInAuthButtonWithoutSpaces();
        var expected = mainPage.refillAccount.getTotalBalanceAfterRefilling(currentBalance, sumInCart);

        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    @DisplayName("5.0 Проверка элементов (заголовок, иконка, текст) в модальном окне с информацией в билете 'Хамам'")
//    void shouldCheckElementsInModalInfoInTicketCard() {
//        Assertions.assertTrue(mainPage.tickets.checkElementSInInfoModal());
//    }

    @Test
    @DisplayName("6.0 Применение скидки в корзине с одним билетом")
    void shouldApplyingDiscountInCartWithOneTicket() {
        mainPage.auth.authWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicketWithClickOnAuthConfirm(DataTickets.TICKET_1.getIndex());
        mainPage.cart.applyDiscount(DataCards.VALID_PROMOCODE_WITH_TEN_PERCENT_DISCOUNT);

        Assertions.assertTrue(mainPage.cart.checkApplyingTenPercentDiscount());
    }

    @Test
    @DisplayName("6.1 Покупка одного билета со скидкой")
    void shouldSuccessPaymentWithDiscountForOneTicket() {
        mainPage.auth.doubleAuthWithCardUid(DataCards.VALID_CARD_UID);
        mainPage.tickets.addTicket(1);
        mainPage.cart.applyDiscount(DataCards.VALID_PROMOCODE_WITH_TEN_PERCENT_DISCOUNT)
                .makeOrder()
                .clickVirtualProcessingButton()
                .returnToTheShopFromVirtualPayment()
                .checkElementsInSuccessPaymentModal();

        var actual = mainPage.orderPayment.getTotalSumInSuccessPaymentModal();
        var expected = mainPage.orderPayment.checkSumAddedTicketWithDiscount(DataTickets.TICKET_1.getPrice());

        Assertions.assertEquals(expected, actual);
    }
}
