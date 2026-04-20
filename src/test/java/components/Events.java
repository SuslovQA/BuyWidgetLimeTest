package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class Events {
    ElementsCollection eventsCards = $$x("//lime-ticket-purchase-widget/div[@class='lime-container']//lime-carousel[2]//div[@class='swiper swiper-initialized swiper-horizontal swiper-backface-hidden']//p-card");
    SelenideElement calendarButton = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
    SelenideElement nowDate = $x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected ng-star-inserted']");

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
