package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tickets {
    ElementsCollection card = $$(By.xpath("//lime-card//button"));
    static ElementsCollection ticketsName = $$(By.xpath("//div[@class='name-string']"));

    public Tickets() {
    }

    public void addTickets(int index) {
        card.get(index).click();

        if (Auth.authModalButton.is(Condition.visible)) {
            Auth.clickModalAuthButton();
        }
    }

    public static String getTicketName(int index) {
        return ticketsName.get(index).getText();
    }
}
