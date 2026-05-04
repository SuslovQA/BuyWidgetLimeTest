package components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public final Auth auth;
    public final Tickets tickets;
    public final Cart cart;
    public final OrderPayment orderPayment;
    public final RefillAccount refillAccount;
    public final Events events;

    SelenideElement logo = $x("//span[@class='logo']");
    SelenideElement ticketsHeader = $$x("//h2").get(0);
    SelenideElement eventsHeader = $$x("//h2").get(1);
    SelenideElement accountHeader = $x("//h3");
    SelenideElement authButton = $x("//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']");
    SelenideElement authButtonText = $x("//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']/span[@class='ng-star-inserted']");
    SelenideElement imgInAuthButton = $x("//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']//img");
    ElementsCollection progressIndicators = $$x("//div[@class='progress-indicator']");
    SelenideElement refillSubtitle = $x("//p[@class='refill-subtitle ng-star-inserted']");
    SelenideElement refillTitle = $x("//div[@class='refill-title']");
    SelenideElement supportLink = $x("//div[@class='support ng-star-inserted']//a");
    SelenideElement supportText = $x("//div[@class='support ng-star-inserted']//span");
    SelenideElement supportLogo = $x("//div[@class='support ng-star-inserted']//img");
    SelenideElement logoInFooter = $x("//div[@class='footer-container']//div[@class='logo-block']/img");
    ElementsCollection aboutUsInFooter = $$x("//div[@class='footer-container']//div[@class='about-us']/div[@class='ng-star-inserted']");
    SelenideElement qr = $x("//div[@class='qr-code']/img");
    String qrSrc = "data:image/png;base64,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";
    SelenideElement qrTitle = $x("//div[@class='qr-code']/div");
    ElementsCollection headersContactsInFooter = $$x("//div[@class='contacts ng-star-inserted']/div");
    ElementsCollection contactsInFooter = $$x("//div[@class='contacts ng-star-inserted']//a");
    SelenideElement datePicker = $x("//p-date-picker");
    ElementsCollection refillSumAmounts = $$x("//div[@class='sum-amount']");
    SelenideElement refillInput = $x("//input[@class='p-inputtext p-component p-inputnumber-input']");
    SelenideElement addRefillInCartButton = $x("//div[@class='free-refill ng-star-inserted']//button[@class='p-ripple p-button p-component']");
    ElementsCollection imgInItemCard = $$x("//img[@class='card-img ng-star-inserted']");
    ElementsCollection priceOnGoodCards = $$x("//lime-carousel[1]//p[@class='lime-full-price']");
    ElementsCollection priceOnEventCards = $$x("//lime-carousel[2]//p[@class='lime-full-price']");
    SelenideElement enabledNavButtonProgressBar = $x("//span[@class='nav-button']");
    ElementsCollection disabledNavButtonProgressBar = $$x("//span[@class='nav-button disabled']");
    ElementsCollection namesOfGoods = $$x("//lime-carousel[1]//div[@class='name-string']");
    ElementsCollection namesOfEvents = $$x("//lime-carousel[2]//div[@class='name-string']");
    ElementsCollection addTicketButtonsInCards = $$x("//lime-carousel[1]//button[@class='p-ripple p-button p-component']");
    ElementsCollection addEventsButtonsInCards = $$x("//lime-carousel[2]//button[@class='p-ripple p-button p-component']");
    SelenideElement allTicketsButton = $x("//lime-carousel[1]//button[@class='p-ripple p-button p-component p-button-rounded']");
    SelenideElement allEventsButton = $x("//lime-carousel[2]//button[@class='p-ripple p-button p-component p-button-rounded']");
    SelenideElement allTicketsButtonText = $x("//lime-carousel[1]//button[@class='p-ripple p-button p-component p-button-rounded']/span");
    SelenideElement allEventsButtonText = $x("//lime-carousel[2]//button[@class='p-ripple p-button p-component p-button-rounded']/span");
    SelenideElement datePickerInput = $x("//input[@class='p-inputtext p-component ng-tns-c2825477640-2 p-datepicker-input p-filled ng-star-inserted']");
    ElementsCollection infoButtonsInCards = $$x("//img[@class='prompt ng-star-inserted']");
    ElementsCollection imgInEventsCard = $$x("//lime-carousel[2]//img[@class='card-img ng-star-inserted']");

    public MainPage() {
        this.auth = new Auth();
        this.tickets = new Tickets();
        this.cart = new Cart();
        this.orderPayment = new OrderPayment();
        this.refillAccount = new RefillAccount();
        this.events = new Events();
    }

    public void checkElementsInMainPage() {
        logo.shouldBe(exist);
        ticketsHeader.shouldHave(exactText("Выбрать билеты"));
        eventsHeader.shouldHave(exactText("Выбрать событие"));
        accountHeader.shouldHave(exactText("Счет"));
        refillSubtitle.shouldHave(exactText("Выберите сумму пополнения"));
        supportLink.shouldBe(enabled).shouldBe(attribute("href", "https://lime-it.ru/contact/?ysclid=mnmu7tbnw4724828617"));
        supportText.shouldHave(exactText("Служба поддержки"));
        supportLogo.shouldBe(exist);
        authButton.shouldBe(enabled).shouldHave(cssValue("border-color", "rgb(255, 185, 135)"));
        authButtonText.shouldHave(exactText("Уже есть карта?"));
        imgInAuthButton.shouldBe(exist).shouldHave(attribute("src", "https://limepay.chudin.ru/buy/card.svg"));
        progressIndicators.get(0)
                .shouldBe(enabled)
                .shouldHave(attribute("style", "width: 50%; left: 0%;"))
                .shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)"));
        progressIndicators.get(1)
                .shouldBe(enabled)
                .shouldHave(attribute("style", "width: 50%; left: 0%;"))
                .shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)"));
        enabledNavButtonProgressBar.shouldBe(visible);
        disabledNavButtonProgressBar.shouldHave(CollectionCondition.size(2));
        priceOnGoodCards.get(0).shouldHave(text("от 100"));
        priceOnGoodCards.get(1).shouldHave(text("300"));
        priceOnGoodCards.get(2).shouldHave(text("999,99"));
        priceOnGoodCards.get(3).shouldNotBe(visible);
        priceOnEventCards.get(0).shouldHave(text("от 100"));
        priceOnEventCards.get(1).shouldHave(text("от 500"));
        priceOnEventCards.get(2).shouldHave(text("от 350"));
        priceOnEventCards.get(3).shouldNotBe(visible);
        datePickerInput.shouldBe(enabled);
        refillTitle.shouldHave(exactText("Введите сумму пополнения"));
        refillSumAmounts.shouldHave(CollectionCondition.size(4));
        refillSumAmounts.get(0).shouldHave(text("100"));
        refillSumAmounts.get(1).shouldHave(text("200"));
        refillSumAmounts.get(2).shouldHave(text("500,55"));
        refillSumAmounts.get(3).shouldHave(text("5 000"));
        refillInput.shouldBe(visible, enabled).shouldHave(attribute("placeholder", "Введите сумму"));
        addRefillInCartButton.shouldBe(visible).shouldBe(disabled).shouldHave(exactText("Добавить в корзину"));
        logoInFooter.shouldBe(exist);
        aboutUsInFooter.get(0).shouldHave(exactText("Улица Розы Люксембург 64, офис 406"));
        aboutUsInFooter.get(1).shouldHave(exactText("Пн - Пт с 8 до 17, Сб и Вс - выходные"));
        qr.shouldBe(exist).shouldHave(attribute("src", qrSrc));
        qrTitle.shouldHave(exactText("QR-код мобильная версия"));
        headersContactsInFooter.get(0).shouldHave(exactText("У Вас возникли затруднения?"));
        headersContactsInFooter.get(1).shouldHave(exactText("Свяжитесь с нами"));
        contactsInFooter.get(0).shouldBe(enabled).shouldHave(exactText("Наш сайт")).shouldBe(attribute("href", "https://lime-it.ru/contact/?ysclid=mnmu7tbnw4724828617"));
        contactsInFooter.get(1).shouldBe(enabled).shouldHave(exactText("88005553535"));
        contactsInFooter.get(2).shouldBe(enabled).shouldHave(exactText("see@lime-it.ru"));
        datePicker.shouldBe(exist);
        imgInItemCard.get(0).shouldBe(exist).shouldHave(attribute("src", "https://limepay.chudin.ru/api/File/Image/156920/GoodTypeTree/Image/42E22A5BBA5B6F90A26786BE9496ED77.png"));
        namesOfGoods.get(0).shouldHave(exactText("Категория билетов"));
        namesOfGoods.get(1).shouldHave(exactText("Билет без категории 1"));
        namesOfGoods.get(2).shouldHave(exactText("5 Билетов"));
        namesOfGoods.shouldHave(CollectionCondition.size(4));
        namesOfEvents.get(0).shouldHave(exactText("Мероприятие 1"));
        namesOfEvents.get(1).shouldHave(exactText("Мероприятие 2"));
        namesOfEvents.get(2).shouldHave(exactText("Мероприятие 3 для витрины"));
        addTicketButtonsInCards.shouldHave(CollectionCondition.size(4))
                .forEach(x -> x.shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)")));
        addEventsButtonsInCards.shouldHave(CollectionCondition.size(4))
                .forEach(x -> x.shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)")));
        allTicketsButton.shouldBe(enabled).shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)"));
        allTicketsButtonText.shouldHave(exactText("Все билеты"));
        allEventsButton.shouldBe(enabled).shouldHave(cssValue("background-color", "rgba(255, 155, 84, 1)"));
        allEventsButtonText.shouldHave(exactText("Все события"));
        infoButtonsInCards.shouldHave(CollectionCondition.size(3))
                .forEach(x -> x.shouldBe(enabled).shouldHave(attribute("src", "https://limepay.chudin.ru/buy/prompt.svg")));
        imgInEventsCard.get(0).shouldBe(exist).shouldHave(attribute("src", "https://limepay.chudin.ru/api/File/Image/156920/Service/Image/D759024D6D0C3587F86E8CFECEB9199D.webp"));
        imgInEventsCard.get(1).shouldBe(exist).shouldHave(attribute("src", "https://limepay.chudin.ru/api/File/Image/156920/Service/Image/E4F050F3E9A18172BFB1C3DD6430A905.webp"));
    }
}