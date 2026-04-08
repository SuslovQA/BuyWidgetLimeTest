package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.AuthData;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class OrderPayment {
    private SelenideElement orderPaymentHeader = $x("//div[@class='user-data']/h4");
    private SelenideElement checkboxAgreements = $x("//input[@type='checkbox']");
    private SelenideElement authFielInOrderPayment = $(By.xpath("//input[@class='p-inputtext p-component auth-input p-filled']"));
    private SelenideElement cartHeader = $x("//div[@class='cart-details']//div[@class='cart-head']/h4");
    private ElementsCollection itemsInCart = $$(By.xpath("//div[@class='modal-overlay']//div[@class='item-name']"));
//    private ElementsCollection processingButtons = $$x("//lime-processing-button//button");
    public static SelenideElement virtualProcessing = $x("//div[contains(text(), ' Онлайн виртуальный ')]/parent::lime-processing-icon/parent::button");
    public static SelenideElement onlineProcessing = $x("//div[contains(text(), ' Оплата картой ')]/parent::lime-processing-icon/parent::button");
    private SelenideElement returnToTheShopLink = $x("//a");
    private SelenideElement successPaymentHeader = $x("//div[@class='order-status']//h2");
    private SelenideElement totalSumInSuccessPaymentModal = $x("//div[@class='total-sum']");


    public void clickCheckboxAgreements() {
        checkboxAgreements.click();
    }

    public boolean checkInputFieldIsEnabled() {
        return authFielInOrderPayment.isEnabled();
    }

    public String getItemNameInCart(int index) {
        return itemsInCart.get(index).getText();
    }

    public void clickVirtualProcessingButton() {
        virtualProcessing.click();
    }

    public void clickOnlineProcessingButton() {
        onlineProcessing.click();
    }

    public void returnToTheShopFromVirtualPayment() {
        returnToTheShopLink.click();
    }

    public String getSuccessPaymentModalHeader() {
        return successPaymentHeader.getText();
    }

    //        orderPaymentHeader.getText().equals("Введите данные для получения заказа");
//        cartHeader.shouldBe(Condition.text("Корзина"));
}
