package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import data.AuthData;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tickets {
    static ElementsCollection card = $$(By.xpath("//lime-card//button"));
    static ElementsCollection ticketsName = $$(By.xpath("//div[@class='name-string']"));

    public Tickets() {
    }

    public Cart addTickets(int index) {
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
}
