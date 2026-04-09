package components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public MainPage() {
        this.auth = new Auth();
        this.tickets = new Tickets();
        this.cart = new Cart();
        this.orderPayment = new OrderPayment();
        this.refillAccount = new RefillAccount();
    }

//        logo.shouldBe(Condition.exist);
//        ticketsHeader.getText().equals("Выбрать билеты");
//        eventsHeader.getText().equals("Выбрать событие");
//        accountHeader.getText().equals("Счет");
//        refillSubtitle.getText().equals("Выберите сумму пополнения");
//        refillTitle.getText().equals("Введите сумму пополнения");
}
