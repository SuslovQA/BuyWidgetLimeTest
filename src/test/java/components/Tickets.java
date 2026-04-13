package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tickets {
    ElementsCollection addTicketButton = $$(By.xpath("//lime-card//button"));
    ElementsCollection ticketsAndCategoryName = $$(By.xpath("//div[@class='name-string']"));
    ElementsCollection addTicketsFromCategoryPlusButton = $$(By.xpath("//p-card[@class='good-type-list']//button[@class='sign-button ng-star-inserted'][2]"));
    SelenideElement successAddToCart = $x("//div[@class='message-block ng-star-inserted']//div[@class='description ng-star-inserted']");
    SelenideElement enabledNavButtonInSwiper = $x("//span[@class='nav-button']");
    SelenideElement allTicketsButton = $(By.xpath("//span[contains(text(), 'Все билеты')]/parent::button"));
    SelenideElement goodTypeTicketItem = $x("//div[@class='good-type-list-item ng-star-inserted']");

    public Tickets addTicketWithClickOnAuthConfirm(int index) {
        addTicketButton.get(index).click();
        Auth.authModalConfirmButton.shouldBe(visible, Duration.ofSeconds(2)).click();

        return this;
    }

    public Tickets addTicket(int index) {
        addTicketButton.get(index).click();

        return this;
    }

    public void addSomeEqualsTickets(int index, int countEqualsTickets) {
        addTicketButton.get(index).click();

            if (Auth.authModalConfirmButton.exists()) {
                Auth.authModalConfirmButton.click();
            }

        for (int i = 0; i < countEqualsTickets - 1; i++) {
            addTicketButton.get(index).click();
        }
//        for (int i = 0; i < countEqualsTickets; i++) {
//            addTicketButton.get(index).click();
//            if (Auth.authModalConfirmButton.exists()) {
//                Auth.authModalConfirmButton.click();
//            }
//        }

    }

    public Tickets addTicketsWithAuthWOnEmail(int index, String email) {
        addTicketButton.get(index).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalInput.sendKeys(email);
            Auth.authModalConfirmButton.click();
        }
        return this;
    }

    public String getTicketNameByIndex(int index) {
        return ticketsAndCategoryName.get(index).getText();
    }


    public void addFirstTicketFromCategory() {
        addTicketButton.get(0).click();
        addTicketsFromCategoryPlusButton.get(0).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalConfirmButton.click();
        }
    }

    public void addTicketFromCategory(int index) {
        if (!goodTypeTicketItem.exists()) {
            addTicketButton.get(0).click();
        }

        addTicketsFromCategoryPlusButton.get(index).click();

        if (Auth.authModalConfirmButton.exists()) {
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