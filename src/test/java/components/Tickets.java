package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Tickets {
    static ElementsCollection card = $$(By.xpath("//lime-card//button"));
    static ElementsCollection ticketsName = $$(By.xpath("//div[@class='name-string']"));
    static ElementsCollection ticketsInCategory = $$(By.xpath("//button[@class='sign-button ng-star-inserted'][2]"));

    public Tickets addTicket(int index) {
        card.get(index).click();

        if (Auth.authModalConfirmButton.is(Condition.visible)) {
            Auth.authModalConfirmButton.click();
        }

        return this;
    }

    public Tickets addTicketsWithAuthWithEmail(int index, String email) {
        card.get(index).click();

        if (Auth.authModalConfirmButton.is(Condition.visible)) {
            Auth.authModalInput.sendKeys(email);
            Auth.authModalConfirmButton.click();
        }
        return this;
    }

    public static String getTicketName(int index) {
        return ticketsName.get(index).getText();
    }


    public Tickets addFirstTicketFromCategory() {
        card.get(0).click();
        ticketsInCategory.get(0).click();

        if (Auth.authModalConfirmButton.is(Condition.visible)) {
            Auth.authModalConfirmButton.click();
        }

        return this;
    }
}
