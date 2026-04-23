package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class Events {
    ElementsCollection eventsCards = $$x("//lime-ticket-purchase-widget/div[@class='lime-container']//lime-carousel[2]//div[@class='swiper swiper-initialized swiper-horizontal swiper-backface-hidden']//p-card");
    ElementsCollection openEvent = $$x("//lime-carousel[2]//button[@class='p-ripple p-button p-component']");
    ElementsCollection eventsNamesInCards = $$x("//lime-carousel[2]//div[@class='name-string']");
    ElementsCollection eventsPricesInCards = $$x("//lime-carousel[2]//p[@class='lime-full-price']");
    SelenideElement calendarButton = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
    SelenideElement nowDate = $x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected ng-star-inserted']");
    SelenideElement allEventsButton = $x("//lime-carousel[2]//button[@class='p-ripple p-button p-component p-button-rounded']");

    public boolean checkCountOfEvents(int sizeOfCollection) {
        for (SelenideElement eventsCard : eventsCards) {
            eventsCard.shouldBe(Condition.visible);
        }

        return eventsCards.size() == sizeOfCollection;
    }

    public boolean checkNowDate() {
        calendarButton.click();

        return nowDate.getAttribute("data-date").equals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-M-dd")));
    }
}
