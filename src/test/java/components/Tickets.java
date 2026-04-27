package components;

import com.codeborne.selenide.*;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tickets {
    ElementsCollection addTicketButton = $$x("//lime-card//button");
    ElementsCollection ticketsAndCategoryName = $$x("//div[@class='name-string']");
    SelenideElement ticketsCategoryContainer =$x("//div[@class='pretty-scrollbar good-type-list-wrapper ng-star-inserted']");
    ElementsCollection ticketsNamesInCategory = $$x("//div[@class='good-type-name']");
    ElementsCollection addTicketsFromCategoryPlusButton = $$x("//p-card[@class='good-type-list']//button[@class='sign-button ng-star-inserted'][2]");
    SelenideElement categoryCloseButton = $x("//div[@class='list-close-button ng-star-inserted']/img");
    SelenideElement successAddToCart = $x("//div[@class='message-block ng-star-inserted']//div[@class='description ng-star-inserted']");
    SelenideElement enabledNavButtonInSwiper = $x("//span[@class='nav-button']");
    SelenideElement allTicketsButton = $x("//lime-carousel[1]//button[@class='p-ripple p-button p-component p-button-rounded']");
    SelenideElement goodTypeTicketItemInCategory = $x("//div[@class='good-type-list-item ng-star-inserted']");
    SelenideElement infoMessage = $x("//div[@class='message-body']/div[@class='description ng-star-inserted']");
    SelenideElement infoButtonInCard = $x("//lime-carousel[1]//img[@class='prompt ng-star-inserted']");
    SelenideElement modalInfo = $x("//div[@class='modal-base']");
    SelenideElement infoModalHeader = $x("//div[@class='info-head']/h2");
    SelenideElement infoModalText = $x("//div[@class='full-info-wrapper']/div[2]");
    SelenideElement infoModalImg = $x("//div[@class='info-head']/img");
    SelenideElement homeBackButtonFromAllTickets = $x("//div[@class='home-back-button']/img");
    SelenideElement logoInHeader = $x("//span[@class='logo']");
    SelenideElement ticketsHeader = $x("//h2/span");
    ElementsCollection ticketsInAll = $$x("//p-card");
    SelenideElement eventsHeader = $x("//lime-carousel[2]//h2");
    SelenideElement refillAccountHeader =$x("//h3");
    ElementsCollection defaultImgInCards = $$x("//div[@class='no-image-plug ng-star-inserted']/img");
    SelenideElement shopCart = $x("//div[@class='shop-cart']//div[@class='p-card p-component']");

    public Tickets addTicketWithClickOnAuthConfirm(int index) {
        addTicketButton.get(index).click();
        Auth.authModalConfirmButton.shouldBe(visible, Duration.ofSeconds(5)).click();
        Auth.authModalConfirmButton.shouldNotBe(visible);

        return this;
    }

    public Tickets addTicket(int index) {
        addTicketButton.get(index).click();
        infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));
        shopCart.shouldBe(visible);

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

            infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));
        }

    }

    public Tickets addTicketsWithAuthOnEmail(int index, String email) {
        addTicketButton.get(index).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalInput.sendKeys(email);
            Auth.authModalConfirmButton.click();
        }

        infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));

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

        infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));
    }

    public void addLastTicketFromCategory() {
        addTicketButton.get(0).click();
        addTicketsFromCategoryPlusButton.last().click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalConfirmButton.click();
        }

        infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));
    }

    public void addTicketFromCategoryByIndex(int index) {
        if (!goodTypeTicketItemInCategory.exists()) {
            addTicketButton.get(0).click();
        }

        addTicketsFromCategoryPlusButton.get(index).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalConfirmButton.click();
        }

        infoMessage.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(exactText("Товар добавлен в корзину"));
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

    public boolean checkTicketAfterClickOnAll() {
        allTicketsButton.click();
        ticketsInAll.shouldHave(CollectionCondition.size(4));

        return homeBackButtonFromAllTickets.exists() && !eventsHeader.exists() && !refillAccountHeader.exists() && !allTicketsButton.exists();
    }

    public boolean checkReturningToMainPageFromAllTicketsAfterClickOnHomeBackButton() {
        homeBackButtonFromAllTickets.click();
        ticketsInAll.shouldHave(CollectionCondition.size(7));

        return !homeBackButtonFromAllTickets.exists() && eventsHeader.exists() && refillAccountHeader.exists() && allTicketsButton.isEnabled();
    }

    public boolean checkReturningToMainPageFromAllTicketsAfterClickOnLogoInHeader() {
        logoInHeader.click();
        ticketsInAll.shouldHave(CollectionCondition.size(7));

        return !homeBackButtonFromAllTickets.exists() && eventsHeader.exists() && refillAccountHeader.exists() && allTicketsButton.isEnabled();
    }

    public boolean checkElementSInInfoModal() {
        infoButtonInCard.click();
        modalInfo.shouldBe(visible);
        return infoModalHeader.has(exactText("Информация")) && infoModalImg.isImage() && infoModalText.has(exactText(DataHelper.TEXT_IN_MODAL_INFO_IN_TICKET_CARD));
    }

    public boolean checkCloseCategoryTickets() {
        ticketsCategoryContainer.shouldNotBe(exist);
        addTicketButton.get(0).click();
        ticketsCategoryContainer.shouldBe(exist);
        categoryCloseButton.shouldBe(enabled).click();

        return !ticketsCategoryContainer.is(exist);
    }

    public boolean checkDefaultImgInCards() {
       defaultImgInCards.shouldHave(CollectionCondition.size(4));

        boolean result = true;

        for (int i = 0; i < defaultImgInCards.size(); i++) {
            if (!defaultImgInCards.get(i).isImage() && !defaultImgInCards.get(i).has(attribute("src", "https://limepay.chudin.ru/buy/no-picture-background.svg"))) {
                result = false;
                System.out.println(result);
                break;
            }
        }

        return result;
    }
}