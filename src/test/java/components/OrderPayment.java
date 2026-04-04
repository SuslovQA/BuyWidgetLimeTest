package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OrderPayment {
    private SelenideElement orderPaymentHeader = $x("//div[@class='user-data']/h4");
    private static SelenideElement checkBoxAgreements = $x("//input[@type='checkbox']");
    private SelenideElement inputCardUid = $(By.xpath("//input[@class='p-inputtext p-component auth-input p-filled']"));

    public OrderPayment() {
        orderPaymentHeader.getText().equals("Введите данные для получения заказа");
        inputCardUid.shouldBe(Condition.disabled);
    }

}
