package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.AuthData;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    SelenideElement logo = $(By.tagName("lime-logo"));
    SelenideElement ticketsHeader = $$(By.tagName("h2")).get(0);
    SelenideElement eventsHeader = $$(By.tagName("h2")).get(1);
    SelenideElement accountHeader = $(By.tagName("h3"));
    SelenideElement refillSubtitle = $(By.className("refill-subtitle"));
    SelenideElement refillTitle = $(By.className("refill-title"));

    public MainPage() {
        logo.shouldBe(Condition.exist);
        ticketsHeader.getText().equals("Выбрать билеты111");
        eventsHeader.getText().equals("Выбрать событие");
        accountHeader.getText().equals("Счет");
        refillSubtitle.getText().equals("Выберите сумму пополнения");
        refillTitle.getText().equals("Введите сумму пополнения");
    }

    public Tickets tickets() {
        return new Tickets();
    }

    public Auth auth() {
        return new Auth();
    }

    public Cart cart() {
        return new Cart();
    }

    public OrderPayment orderPayment() {
        return new OrderPayment();
    }

    public Message message() { return new Message();}
}
