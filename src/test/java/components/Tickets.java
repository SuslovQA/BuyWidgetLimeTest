package components;

import com.codeborne.selenide.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tickets {
    ElementsCollection addTicketButton = $$x("//lime-card//button");
    ElementsCollection ticketsAndCategoryName = $$x("//div[@class='name-string']");
    ElementsCollection addTicketsFromCategoryPlusButton = $$x("//p-card[@class='good-type-list']//button[@class='sign-button ng-star-inserted'][2]");
    SelenideElement successAddToCart = $x("//div[@class='message-block ng-star-inserted']//div[@class='description ng-star-inserted']");
    SelenideElement enabledNavButtonInSwiper = $x("//span[@class='nav-button']");
    SelenideElement allTicketsButton = $x("//span[contains(text(), 'Все билеты')]/parent::button");
    SelenideElement goodTypeTicketItem = $x("//div[@class='good-type-list-item ng-star-inserted']");
    SelenideElement infoMessage = $x("//div[@class='message-body']/div[@class='description ng-star-inserted']");

    public Tickets addTicketWithClickOnAuthConfirm(int index) {
        addTicketButton.get(index).click();
        Auth.authModalConfirmButton.shouldBe(visible, Duration.ofSeconds(5)).click();
        Auth.authModalConfirmButton.shouldNotBe(visible);

        return this;
    }

    public Tickets addTicket(int index) {
        addTicketButton.get(index).click();
        infoMessage.shouldBe(visible).shouldHave(text("Товар добавлен в корзину"));

        return this;
    }

    public void addSomeEqualsTickets(int index, int countEqualsTickets) {
        for (int i = 0; i < countEqualsTickets; i++) {
            addTicketButton.get(index).click();
            addTicketButton.get(index).shouldBe(enabled);

            if (Auth.authModalConfirmButton.exists()) {
                Auth.authModalConfirmButton.click();

                Auth.authModalConfirmButton.shouldNotBe(visible);
            }
        }

    }

    public Tickets addTicketsWithAuthOnEmail(int index, String email) {
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