package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tickets {
    static ElementsCollection card = $$(By.xpath("//lime-card//button"));
    static ElementsCollection ticketsName = $$(By.xpath("//div[@class='name-string']"));
    static ElementsCollection ticketsInCategory = $$(By.xpath("//button[@class='sign-button ng-star-inserted']"));

    public Tickets() {
    }

    public Cart addTicket(int index) {
        card.get(index).click();

        if (Auth.authModalButton.is(Condition.visible)) {
            Auth.clickModalAuthButton();
        }
        return new Cart();
    }

    public Cart addTicketsWithAuthWithEmail(int index, String email) {
        card.get(index).click();

        if (Auth.authModalButton.is(Condition.visible)) {
            Auth.authModalInput.sendKeys(email);
            Auth.authModalButton.click();
        }
        return new Cart();
    }

    public static String getTicketName(int index) {
        return ticketsName.get(index).getText();
    }

    public Cart cart() {
        return new Cart();
    }

    public Cart addFirstTicketFromCategory() {
        card.get(1).click();

        if (Auth.authModalButton.is(Condition.exist)) {
            Auth.authModalButton.click();
        }

        ticketsInCategory.get(0).click();

        return new Cart();
    }
}
