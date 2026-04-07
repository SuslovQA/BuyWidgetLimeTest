package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tickets {
    ElementsCollection addTicketButton = $$(By.xpath("//lime-card//button"));
    ElementsCollection ticketsName = $$(By.xpath("//div[@class='name-string']"));
    ElementsCollection ticketsInCategory = $$(By.xpath("//button[@class='sign-button ng-star-inserted'][2]"));
    SelenideElement successAddToCart = $x("//div[@class='message-block ng-star-inserted']//div[@class='description ng-star-inserted']");
    SelenideElement enabledNavButtonInSwiper = $x("//span[@class='nav-button']");
    SelenideElement allTicketsButton = $(By.xpath("//span[contains(text(), 'Все билеты')]/parent::button"));

    public Tickets addTicketWithClickOnAuthConfirm(int index) {
        addTicketButton.get(index).click();

            if (Auth.authModalConfirmButton.isDisplayed()) {
                Auth.authModalConfirmButton.click();
            }


        return this;
    }

    public Tickets addTicket(int index) {
        addTicketButton.get(index).click();

        return this;
    }

    public void addSomeEqualsTickets(int index, int countEqualsTickets) {
        for (int i = 0; i < countEqualsTickets; i++) {
            addTicketWithClickOnAuthConfirm(index);
        }

    }

    public Tickets addTicketsWithAuthWithEmail(int index, String email) {
        addTicketButton.get(index).click();

        if (Auth.authModalConfirmButton.is(visible)) {
            Auth.authModalInput.sendKeys(email);
            Auth.authModalConfirmButton.click();
        }
        return this;
    }

    public String getTicketNameByIndex(int index) {
        return ticketsName.get(index).getText();
    }


    public void addFirstTicketFromCategory() {
        addTicketButton.get(0).click();
        ticketsInCategory.get(0).click();

        if (Auth.authModalConfirmButton.is(visible)) {
            Auth.authModalConfirmButton.click();
        }
    }

    public String getSuccessAddToCartMessage() {
        return successAddToCart.shouldBe(visible).getText();
    }

    public Tickets clickEnabledNavButtonInSwiper() {
        enabledNavButtonInSwiper.click();

        return this;
    }

    public Tickets clickOnAllTicketsButton() {
        allTicketsButton.click();

        return this;
    }
}