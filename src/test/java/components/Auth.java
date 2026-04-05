package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Auth {
    static SelenideElement authModalInput = $(By.xpath("//input[@class='p-inputtext p-component auth-input']"));
    static SelenideElement authModalButton = $(By.xpath("//div[@class='confirm-button-container']//button"));
    SelenideElement authIconContainer = $(By.xpath("//div[@class='icon-container']"));
    SelenideElement authButton = $(By.xpath("//p-button//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']"));

    //    private String authData;
//    private int authIndex;
//    private String numberToString;


    public Auth() {
        authIconContainer.shouldBe(Condition.visible, Condition.enabled);
    }

    public Tickets authWithCardUid(String cardUid) {
        authButton.click();
        authModalInput.sendKeys(cardUid);
        authModalButton.click();

        authButton.getText().equals(cardUid.trim());

        return new Tickets();
    }

    public static void clickModalAuthButton() {
        authModalButton.click();
    }

//    public void authWithData() {
//        switch (authIndex) {
//            case 0: authData =;
//        }
//    }

    public Cart cart() {
        authModalInput.sendKeys("1234567890");
        authModalButton.click();

        return new Cart();
    }

    public Tickets tickets() {
        return new Tickets();
    }
}
