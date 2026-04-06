package components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Auth {
    static SelenideElement authModalInput = $(By.xpath("//input[@class='p-inputtext p-component auth-input']"));
    static SelenideElement authModalConfirmButton = $(By.xpath("//div[@class='confirm-button-container']//button"));
    SelenideElement authIconContainer = $(By.xpath("//div[@class='icon-container']"));
    static SelenideElement authButton = $(By.xpath("//p-button//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']"));

    public Auth authWithCardUid(String cardUid) {
        authButton.click();
        authModalInput.sendKeys(cardUid);
        authModalConfirmButton.click();

        return this;
    }

    public static void clickModalAuthButton() {
        authModalConfirmButton.click();
    }

//  authButton.getText().equals(cardUid.trim());

//    public void authWithData() {
//        switch (authIndex) {
//            case 0: authData =;
//        }
//    }
}
