package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class OrderPayment {
    SelenideElement orderPaymentHeader = $x("//div[@class='user-data']/h4");
    SelenideElement checkboxAgreements = $x("//input[@type='checkbox']");
    SelenideElement authFieldInOrderPayment = $(By.xpath("//input[@class='p-inputtext p-component auth-input p-filled']"));
    SelenideElement cartHeader = $x("//div[@class='cart-details']//div[@class='cart-head']/h4");
    ElementsCollection itemsInCart = $$x("//div[@class='modal-overlay']//div[@class='item-name']");
    static SelenideElement virtualProcessing = $x("//div[contains(text(), ' Онлайн виртуальный ')]/parent::lime-processing-icon/parent::button");
    static SelenideElement onlineProcessing = $x("//div[contains(text(), ' Оплата картой ')]/parent::lime-processing-icon/parent::button");
    SelenideElement returnToTheShopLink = $x("//a");
    SelenideElement successPaymentHeader = $x("//div[@class='order-status']//h2");
    SelenideElement totalSumInSuccessPaymentModal = $x("//div[@class='total-sum']");
    SelenideElement textWithEmailInSuccessPaymentModal = $x("//div[@class='order-status']//div[@class='get-tickets-message ng-star-inserted']/span[1]");
    SelenideElement textForDownloadingTicketsInSuccessPaymentModal = $x("//div[@class='order-status']//div[@class='get-tickets-message ng-star-inserted']/span[2]");
    SelenideElement imgStatusOk = $x("//div[@class='paid-order']/img");
    SelenideElement downloadTicketsButton = $x("//button[@class='download-ticket ng-star-inserted']");
    SelenideElement downloadTicketsImg = $x("//button[@class='download-ticket ng-star-inserted']/img");
    SelenideElement closeStatusPaymentModalButton = $x("//button[@class='close-button ng-star-inserted']");

    public void clickCheckboxAgreements() {
        checkboxAgreements.click();
    }

    public boolean checkInputFieldIsEnabled() {
        return authFieldInOrderPayment.isEnabled();
    }

    public String getItemNameInCart(int index) {
        return itemsInCart.get(index).getText();
    }

    public OrderPayment clickVirtualProcessingButton() {
        virtualProcessing.click();

        return this;
    }

    public void clickOnlineProcessingButton() {
        onlineProcessing.click();
    }

    public OrderPayment returnToTheShopFromVirtualPayment() {
        returnToTheShopLink.click();

        return this;
    }

    public void returnToTheShopFromVirtualPaymentAndCloseStatusModal() {
        returnToTheShopLink.click();
        closeStatusPaymentModalButton.click();
    }

    public OrderPayment checkElementsInSuccessPaymentModal() {
        successPaymentHeader.shouldHave(Condition.text("Оплата прошла успешно!"));
        textForDownloadingTicketsInSuccessPaymentModal.shouldHave(Condition.text("Билеты будут скачаны автоматически, если нет, то нажмите на кнопку:"));
        imgStatusOk.shouldBe(Condition.exist);
        downloadTicketsButton.shouldBe(Condition.enabled).shouldHave(Condition.text("Скачать билеты"));
        downloadTicketsImg.shouldBe(Condition.exist);
        return this;
    }

    public String getSuccessPaymentModalHeader() {
        return successPaymentHeader.getText();
    }

    public String getTotalSumInSuccessPaymentModal() {
        successPaymentHeader.shouldBe(Condition.visible);

        String[] splitWords = totalSumInSuccessPaymentModal.getText().split(" ");

        String result = splitWords[1].replace(',', '.');

        if (!result.contains(".")) {
           result += ".0";
        }

        return result;
    }

    public String checkSumAddedTicketWithDiscount(double ticketPrice) {
        return String.valueOf(ticketPrice * 0.9);
    }

    public String getTextWithEmailInSuccessPaymentModal() {
        return textWithEmailInSuccessPaymentModal.getText();
    }
}