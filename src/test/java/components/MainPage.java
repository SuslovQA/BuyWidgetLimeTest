package components;

import com.codeborne.selenide.Condition;
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

    SelenideElement logo = $(By.tagName("lime-logo"));
    SelenideElement ticketsHeader = $$(By.tagName("h2")).get(0);
    SelenideElement eventsHeader = $$(By.tagName("h2")).get(1);
    SelenideElement accountHeader = $(By.tagName("h3"));
    SelenideElement refillSubtitle = $(By.className("refill-subtitle"));
    SelenideElement refillTitle = $(By.className("refill-title"));
    SelenideElement supportLink = $x("//div[@class='support ng-star-inserted']//a");
    SelenideElement supportText = $x("//div[@class='support ng-star-inserted']//span");
    SelenideElement supportLogo = $x("//div[@class='support ng-star-inserted']//img");
    SelenideElement logoInFooter = $x("//div[@class='footer-container']//div[@class='logo-block']/img");
    ElementsCollection aboutUsInFooter = $$x("//div[@class='footer-container']//div[@class='about-us']/div[@class='ng-star-inserted']");
    SelenideElement qrInFooter = $x("//div[@class='qr-code']/img");
    ElementsCollection headersContactsInFooter = $$x("//div[@class='contacts ng-star-inserted']/div");
    ElementsCollection contactsInFooter = $$x("//div[@class='contacts ng-star-inserted']//a");

    public MainPage() {
        this.auth = new Auth();
        this.tickets = new Tickets();
        this.cart = new Cart();
        this.orderPayment = new OrderPayment();
        this.refillAccount = new RefillAccount();
    }

    public void checkElementsInMainPage() {
        logo.shouldBe(exist);
        ticketsHeader.shouldHave(text("Выбрать билеты"));
        eventsHeader.shouldHave (text("Выбрать событие"));
        accountHeader.shouldHave (text("Счет"));
        refillSubtitle.shouldHave (text("Выберите сумму пополнения"));
        supportLink.shouldBe(enabled);
        supportText.shouldHave(text("Служба поддержки"));
        supportLogo.shouldBe(exist);
        refillTitle.shouldHave (text("Введите сумму пополнения"));
        logoInFooter.shouldBe(exist);
        aboutUsInFooter.get(0).shouldHave(text("Улица Розы Люксембург 64, офис 406"));
        aboutUsInFooter.get(1).shouldHave(text("Пн - Пт с 9 до 18, Сб и Вс - выходные"));
        qrInFooter.shouldBe(exist);
        headersContactsInFooter.get(0).shouldHave(text("У Вас возникли затруднения?"));
        headersContactsInFooter.get(1).shouldHave(text("Свяжитесь с нами"));
        contactsInFooter.get(0).shouldBe(enabled).shouldHave(text("Наш сайт"));
        contactsInFooter.get(1).shouldBe(enabled).shouldHave(text("88005553535"));
        contactsInFooter.get(2).shouldBe(enabled).shouldHave(text("see@lime-it.ru"));
    }
}
