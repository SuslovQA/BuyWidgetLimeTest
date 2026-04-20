package components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public final Auth auth;
    public final Tickets tickets;
    public final Cart cart;
    public final OrderPayment orderPayment;
    public final RefillAccount refillAccount;
    public final Events events;

    SelenideElement logo = $(By.tagName("lime-logo"));
    SelenideElement ticketsHeader = $$x("//h2").get(0);
    SelenideElement eventsHeader = $$x("//h2").get(1);
    SelenideElement accountHeader = $x("//h3");
    ElementsCollection progressIndicators = $$x("//div[@class='progress-indicator']");
    SelenideElement refillSubtitle = $x("//p[@class='refill-subtitle ng-star-inserted']");
    SelenideElement refillTitle = $x("//div[@class='refill-title']");
    SelenideElement supportLink = $x("//div[@class='support ng-star-inserted']//a");
    SelenideElement supportText = $x("//div[@class='support ng-star-inserted']//span");
    SelenideElement supportLogo = $x("//div[@class='support ng-star-inserted']//img");
    SelenideElement logoInFooter = $x("//div[@class='footer-container']//div[@class='logo-block']/img");
    ElementsCollection aboutUsInFooter = $$x("//div[@class='footer-container']//div[@class='about-us']/div[@class='ng-star-inserted']");
    SelenideElement qr = $x("//div[@class='qr-code']/img");
    String qrSrc = "data:image/png;base64,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";
    SelenideElement qrTitle = $x("//div[@class='qr-code']/div");
    ElementsCollection headersContactsInFooter = $$x("//div[@class='contacts ng-star-inserted']/div");
    ElementsCollection contactsInFooter = $$x("//div[@class='contacts ng-star-inserted']//a");
    SelenideElement datePicker = $x("//p-date-picker");
    ElementsCollection refillSumAmounts = $$x("//div[@class='sum-amount']");
    SelenideElement refillInput = $x("//input[@class='p-inputtext p-component p-inputnumber-input']");
    SelenideElement addRefillInCartButton = $x("//div[@class='free-refill ng-star-inserted']//button[@class='p-ripple p-button p-component']");

    public MainPage() {
        this.auth = new Auth();
        this.tickets = new Tickets();
        this.cart = new Cart();
        this.orderPayment = new OrderPayment();
        this.refillAccount = new RefillAccount();
        this.events = new Events();
    }

    public void checkElementsInMainPage() {
        logo.shouldBe(exist);
        ticketsHeader.shouldHave(text("Выбрать билеты"));
        eventsHeader.shouldHave (text("Выбрать событие"));
        accountHeader.shouldHave (text("Счет"));
        refillSubtitle.shouldHave (text("Выберите сумму пополнения"));
        supportLink.shouldBe(enabled).shouldBe(attribute("href", "https://lime-it.ru/contact/?ysclid=mnmu7tbnw4724828617"));
        supportText.shouldHave(text("Служба поддержки"));
        supportLogo.shouldBe(exist);
        refillTitle.shouldHave (text("Введите сумму пополнения"));
        refillSumAmounts.shouldHave(CollectionCondition.size(4));
        refillSumAmounts.get(0).shouldHave(text("100"));
        refillSumAmounts.get(1).shouldHave(text("200"));
        refillSumAmounts.get(2).shouldHave(text("500"));
        refillSumAmounts.get(3).shouldHave(text("1 000"));
        refillInput.shouldBe(visible, enabled).shouldHave(attribute("placeholder", "Введите сумму"));
        addRefillInCartButton.shouldBe(visible).shouldBe(disabled).shouldHave(text("Добавить в корзину"));
        logoInFooter.shouldBe(exist);
        aboutUsInFooter.get(0).shouldHave(text("Улица Розы Люксембург 64, офис 406"));
        aboutUsInFooter.get(1).shouldHave(text("Пн - Пт с 9 до 18, Сб и Вс - выходные"));
        qr.shouldBe(exist).shouldHave(attribute("src", qrSrc));
        qrTitle.shouldHave(text("QR-код мобильная версия"));
        headersContactsInFooter.get(0).shouldHave(text("У Вас возникли затруднения?"));
        headersContactsInFooter.get(1).shouldHave(text("Свяжитесь с нами"));
        contactsInFooter.get(0).shouldBe(enabled).shouldHave(text("Наш сайт")).shouldBe(attribute("href", "https://lime-it.ru/contact/?ysclid=mnmu7tbnw4724828617"));
        contactsInFooter.get(1).shouldBe(enabled).shouldHave(text("88005553535"));
        contactsInFooter.get(2).shouldBe(enabled).shouldHave(text("see@lime-it.ru"));
        datePicker.shouldBe(exist);
    }
}
