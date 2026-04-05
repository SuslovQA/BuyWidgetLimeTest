package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Message {
    SelenideElement errorMessage = $(By.xpath("//div[@class='head ng-star-inserted']"));
    SelenideElement errorMessageText = $(By.xpath("//div[@class='message-body']//div[@class='description ng-star-inserted']"));

    public Message() {
        errorMessage.shouldBe(Condition.visible).getText().trim().equals("Ошибка");
    }

    public String getErrorMessage() {
        return errorMessageText.shouldBe(Condition.visible).getText().trim();
    }

}
