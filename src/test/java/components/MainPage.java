package components;

import com.codeborne.selenide.Condition;
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
    }
}
