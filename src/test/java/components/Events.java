package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataEvents;
import data.DataHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class Events {
    ElementsCollection eventsCards = $$x("//lime-ticket-purchase-widget/div[@class='lime-container']//lime-carousel[2]//div[@class='swiper swiper-initialized swiper-horizontal swiper-backface-hidden']//p-card");
    ElementsCollection openEventSchedule = $$x("//lime-carousel[2]//button[@class='p-ripple p-button p-component']");
    ElementsCollection eventsNamesInCards = $$x("//lime-carousel[2]//div[@class='name-string']");
    ElementsCollection eventsPricesInCards = $$x("//lime-carousel[2]//p[@class='lime-full-price']");
    SelenideElement calendarButton = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
    SelenideElement nowDate = $x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected ng-star-inserted']");
    SelenideElement allEventsButton = $x("//lime-carousel[2]//button[@class='p-ripple p-button p-component p-button-rounded']");
    SelenideElement datePickerInput = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
    SelenideElement eventScheduleModal = $x("//div[@class='event-schedule']");
    ElementsCollection addEventInCartButton = $$x("//lime-event-price-item//img[@limeimg='plus-sign.svg']/parent::button");
    ElementsCollection availableSeatsInSchedule = $$x("//div[@class='event-time-space-info'][2]/div[1]");
    ElementsCollection countAddedTicketsInSchedule = $$x("//div[@class='event-schedule-price ng-star-inserted']//div[@class='items-amount']");
    SelenideElement infoMessage = $x("//div[@class='message-body']/div");
    SelenideElement cart = $x("//lime-products-list");
    SelenideElement eventNameHeaderInSchedule = $x("//div[@class='schedule-head']/h2");
    SelenideElement datePickerInputInSchedule = $x("//div[@class='event-schedule']//input");
    SelenideElement makeOrderButton = $x("//lime-event-schedule-list//button[@class='p-ripple p-button p-component']");
    SelenideElement closeScheduleButton = $x("//button[@class='close-button ng-star-inserted']");
    SelenideElement eventGroupButton = $x("//button[@class='event-group']");
    SelenideElement activeEventGroupButton = $x("//button[@class='event-group active']");
    SelenideElement enabledNavButtonInSwiper = $x("//lime-carousel[2]//span[@class='nav-button']");

    public int getCountOfEvents() {
        eventsCards.get(0).scrollIntoView(true);

        for (SelenideElement eventsCard : eventsCards) {
            eventsCard.shouldBe(Condition.visible);
        }

        return eventsCards.size();
    }

    public void openEventScheduleByIndex(int eventIndex) {
        openEventSchedule.get(eventIndex).click();
        eventScheduleModal.shouldBe(Condition.visible);
        eventNameHeaderInSchedule.shouldHave(Condition.exactText(eventsNamesInCards.get(eventIndex).getText()));
    }

    public void checkElementsInEventSchedule(int eventIndex) {
        String currentDateInDataPicker = datePickerInput.getValue();

        openEventSchedule.get(eventIndex).click();
        eventScheduleModal.shouldBe(Condition.visible);
        eventNameHeaderInSchedule.shouldHave(Condition.exactText(getEventsNames(eventIndex)));
        makeOrderButton.shouldNotBe(Condition.exist);
        datePickerInputInSchedule.shouldBe(Condition.visible);
        closeScheduleButton.shouldBe(Condition.enabled);
        datePickerInputInSchedule.shouldHave(Condition.value(currentDateInDataPicker));
    }

    public Events addEvent(int eventIndex, int ticketIndex) {
        openEventSchedule.get(eventIndex).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalConfirmButton.click();
        }

        int currentAvailableSeatsInSchedule = Integer.parseInt(availableSeatsInSchedule.get(ticketIndex).shouldBe(Condition.visible).getText());
        int currentCountAddedTickets = Integer.parseInt(countAddedTicketsInSchedule.get(ticketIndex).shouldBe(Condition.visible).getText());

        eventScheduleModal.shouldBe(Condition.visible);
        addEventInCartButton.get(ticketIndex).click();

        infoMessage.shouldBe(Condition.visible).shouldHave(Condition.exactText("Товар добавлен в корзину"));

        String resul2 = String.valueOf(currentAvailableSeatsInSchedule - 1);
        String result = String.valueOf(currentCountAddedTickets + 1);

        availableSeatsInSchedule.get(ticketIndex)
                .shouldHave(Condition.text(resul2));

        countAddedTicketsInSchedule.get(ticketIndex)
                .shouldHave(Condition.text(result));

        cart.shouldBe(Condition.exist);

        return this;
    }

    public Events addEventWithClientCategory(int eventIndex, int categoryIndex, int ticketIndex) {
        if (!eventScheduleModal.is(Condition.visible)) openEventSchedule.get(eventIndex).click();

        if (Auth.authModalConfirmButton.exists()) Auth.authModalConfirmButton.click();

        int currentAvailableSeatsInSchedule = Integer.parseInt(availableSeatsInSchedule.get(ticketIndex).shouldBe(Condition.visible).getText());
        int currentCountAddedTickets;

        if (categoryIndex == 0) {
            currentCountAddedTickets = Integer.parseInt(DataHelper.getEvenElements(countAddedTicketsInSchedule)
                    .get(ticketIndex)
                    .shouldBe(Condition.visible)
                    .getText());
            DataHelper.getEvenElements(addEventInCartButton).get(ticketIndex).shouldBe(Condition.enabled).click();
            infoMessage.shouldBe(Condition.visible).shouldHave(Condition.exactText("Товар добавлен в корзину"));
            String recalculatedAddedTickets = String.valueOf(currentCountAddedTickets + 1);
            DataHelper.getEvenElements(countAddedTicketsInSchedule)
                    .get(ticketIndex)
                    .shouldHave(Condition.text(recalculatedAddedTickets));
        } else if (categoryIndex == 1) {
            currentCountAddedTickets = Integer.parseInt(DataHelper.getOddElements(countAddedTicketsInSchedule)
                    .get(ticketIndex)
                    .shouldBe(Condition.visible)
                    .getText());
            DataHelper.getOddElements(addEventInCartButton).get(ticketIndex).shouldBe(Condition.enabled).click();
            infoMessage.shouldBe(Condition.visible).shouldHave(Condition.exactText("Товар добавлен в корзину"));
            String recalculatedAddedTickets = String.valueOf(currentCountAddedTickets + 1);
            DataHelper.getOddElements(countAddedTicketsInSchedule)
                    .get(ticketIndex)
                    .shouldHave(Condition.text(recalculatedAddedTickets));
        }

        String recalculatedAvailableSeats = String.valueOf(currentAvailableSeatsInSchedule - 1);
        availableSeatsInSchedule.get(ticketIndex)
                .shouldHave(Condition.text(recalculatedAvailableSeats));

        cart.shouldBe(Condition.exist);

        return this;
    }

    public String getEventsNames(int eventIndex) {
        return eventsNamesInCards.get(eventIndex).getText();
    }

    public boolean checkDateInDatePickerInput() {
        return datePickerInput.getValue()
                .equals(
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"))
                                + " - "
                                + LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd.MM"))
                );
    }

    public String getEventNameInSchedule() {
        eventScheduleModal.shouldBe(Condition.visible);

        return eventNameHeaderInSchedule.getText();
    }

    public void makeOrder() {
        makeOrderButton.shouldBe(Condition.enabled).click();
        cart.shouldBe(Condition.visible);
    }

    public boolean checkMinEventsTicketsPrices(int eventIndex) {
        String[] splitElements = eventsPricesInCards.get(eventIndex).getText().split(" ");
        String elem = Double.valueOf(splitElements[1]).toString();

        Map<Integer, Double> eventsAndPrices = new HashMap<>();
        eventsAndPrices.put(DataEvents.EVENT_1.getIndex(), DataEvents.EVENT_1.getPrice());
        eventsAndPrices.put(DataEvents.EVENT_2.getIndex(), DataEvents.EVENT_2.getChildOrFirstSeatTypePrice());
        eventsAndPrices.put(DataEvents.EVENT_3.getIndex(), DataEvents.EVENT_3.getPrice());
        eventsAndPrices.put(DataEvents.EVENT_4.getIndex(), DataEvents.EVENT_4.getChildOrFirstSeatTypePrice());

        System.out.println(eventsAndPrices.get(eventIndex).toString());
        System.out.println(elem);

       return eventsAndPrices.get(eventIndex).toString().equals(elem);
    }
}
