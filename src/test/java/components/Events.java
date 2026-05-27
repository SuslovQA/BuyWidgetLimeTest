package components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataEvents;
import data.DataEventsTickets;
import data.DataHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class Events {
    ElementsCollection eventsCards = $$x("//lime-ticket-purchase-widget/div[@class='lime-container']//lime-carousel[2]//div[@class='swiper swiper-initialized swiper-horizontal swiper-backface-hidden']//p-card");
    ElementsCollection openEventSchedule = $$x("//lime-carousel[2]//button[@class='p-ripple p-button p-component']");
    ElementsCollection eventsNamesInCards = $$x("//lime-carousel[2]//div[@class='name-string']");
    ElementsCollection eventsPricesInCards = $$x("//lime-carousel[2]//p[@class='lime-full-price']");
    SelenideElement nowDate = $x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected ng-star-inserted']");
    ElementsCollection activeDateInDatePicker = $$x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day ng-star-inserted']");
    ElementsCollection disabledDateInDatePicker = $$x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-disabled ng-star-inserted']");
    ElementsCollection selectedRangeOfDateInDatePicker = $$x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected-range ng-star-inserted']");
    SelenideElement selectedDateInDatePicker = $x("//span[@class='p-ripple ng-tns-c2825477640-2 p-datepicker-day p-datepicker-day-selected ng-star-inserted']");
    SelenideElement allEventsButton = $x("//lime-carousel[2]//button[@class='p-ripple p-button p-component p-button-rounded']");
    SelenideElement datePickerInputButton = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
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
    SelenideElement disabledNavButtonInSwiper = $x("//lime-carousel[2]//span[@class='nav-button disabled']");
    ElementsCollection eventsInAll = $$x("//p-card");
    SelenideElement homeBackButtonFromAllEvents = $x("//div[@class='home-back-button']/img");
    SelenideElement logoInHeader = $x("//span[@class='logo']");
    SelenideElement ticketsHeader = $x("//lime-carousel[1]//h2");
    SelenideElement refillAccountHeader =$x("//h3");
    SelenideElement eventDateDay = $x("//span[@class='event-date-number']");
    SelenideElement eventDateWeekday = $x("//span[@class='event-date-weekday']");
    SelenideElement eventDateMonth = $x("//span[@class='event-date-Month']");

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
        String currentDateInDataPicker = datePickerInputButton.getValue();

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

    public boolean checkDefaultDateIntervalInDatePickerInput() {
        return datePickerInputButton.getValue()
                .equals(
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"))
                                + " - "
                                + LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd.MM"))
                );
    }

    public boolean checkDefaultSelectedDateInDatePicker() {
        datePickerInputButton.click();

        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        int selectedDayOfMonth = Integer.parseInt(nowDate.getText());

        return selectedDayOfMonth == currentDayOfMonth;
    }

    public boolean checkChangesInDatePickerWithOneDayOfPeriod() {
        datePickerInputButton.click();

        int random = DataHelper.getRandomInt(1, selectedRangeOfDateInDatePicker.size() - 1);
        String randomDayInSelectedRange = selectedRangeOfDateInDatePicker.get(random).getText();
        selectedRangeOfDateInDatePicker.get(random).click();

        String selectedDayInInput = datePickerInputButton.getValue();

        String[] splittingSelectedDayInInput = selectedDayInInput.split("\\.");

        selectedDateInDatePicker.shouldHave(Condition.exactText(splittingSelectedDayInInput[0]));

        disabledNavButtonInSwiper.click();

        String pickedDate = datePickerInputButton.getValue();
        String currentDay = String.valueOf(LocalDate.now().plusDays(random + 1).getDayOfMonth());
        String currentDate = LocalDate.now().plusDays(random + 1).format(DateTimeFormatter.ofPattern("dd.MM"));

        return currentDay.equals(randomDayInSelectedRange) && currentDay.equals(selectedDateInDatePicker.getText()) && pickedDate.equals(currentDate);
    }

    public boolean checkEventDateInCartAfterSelectingDateInCalendar(int index, int ticketIndex) {
        datePickerInputButton.click();

        int random = DataHelper.getRandomInt(1, selectedRangeOfDateInDatePicker.size() - 1);
        String randomDayInSelectedRange = selectedRangeOfDateInDatePicker.get(random).getText();
        selectedRangeOfDateInDatePicker.get(random).click();

        String selectedDayInInput = datePickerInputButton.getValue();

        String[] splittingSelectedDayInInput = selectedDayInInput.split("\\.");

        selectedDateInDatePicker.shouldHave(Condition.exactText(splittingSelectedDayInInput[0]));
        selectedDateInDatePicker.click();

        addEvent(index, ticketIndex);
        return eventDateDay.has(Condition.exactText(selectedDayInInput)) && eventDateMonth.has(Condition.exactText(datePickerInputInSchedule.getText().split("\\.")[0]));
    }

    public String getEventNameInSchedule() {
        eventScheduleModal.shouldBe(Condition.visible);

        return eventNameHeaderInSchedule.getText();
    }

    public void makeOrder() {
        makeOrderButton.shouldBe(Condition.enabled).click();
        cart.shouldBe(Condition.visible);
    }

    public boolean checkMinPricesEventsTicketsInCard(int eventIndex) {
        if (eventIndex > 2) {
            enabledNavButtonInSwiper.click();
        }
        String[] splittedElements = eventsPricesInCards.get(eventIndex).getText().split(" ");
        String element = splittedElements[1] + ".0";

        Map<Integer, Double> eventsAndPrices = new HashMap<>();
        eventsAndPrices.put(DataEvents.EVENT_1.getIndex(), DataEventsTickets.EVENT_1_TICKET.getPrice());
        eventsAndPrices.put(DataEvents.EVENT_2.getIndex(), DataEventsTickets.EVENT_2_TICKET_CHILD.getPrice());

        eventsAndPrices.put(DataEvents.EVENT_3.getIndex(), DataEventsTickets.EVENT_3_TICKET.getPrice());
        eventsAndPrices.put(DataEvents.EVENT_4.getIndex(), DataEvents.EVENT_4.getChildOrFirstSeatTypePrice());

       return eventsAndPrices.get(eventIndex).toString().equals(element);
    }

    public boolean checkEventsAfterClickOnAll() {
        allEventsButton.click();
        eventsInAll.shouldHave(CollectionCondition.size(4));

        return homeBackButtonFromAllEvents.exists() && !ticketsHeader.exists() && !refillAccountHeader.exists() && !allEventsButton.exists();
    }

    public boolean checkReturningToMainPageFromAllEventsAfterClickOnHomeBackButton() {
        allEventsButton.click();
        homeBackButtonFromAllEvents.click();
        eventsInAll.shouldHave(CollectionCondition.size(8));

        return !homeBackButtonFromAllEvents.exists() && ticketsHeader.exists() && refillAccountHeader.exists() && allEventsButton.isEnabled();
    }

    public boolean checkReturningToMainPageFromAllEventsAfterClickOnLogoInHeader() {
        allEventsButton.click();
        logoInHeader.click();
        eventsInAll.shouldHave(CollectionCondition.size(8));

        return !homeBackButtonFromAllEvents.exists() && ticketsHeader.exists() && refillAccountHeader.exists() && allEventsButton.isEnabled();
    }


}