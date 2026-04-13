package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Auth {
    static SelenideElement authModalInput = $(By.xpath("//input[@class='p-inputtext p-component auth-input']"));
    static SelenideElement authModalConfirmButton = $(By.xpath("//div[@class='confirm-button-container']//button"));
    SelenideElement authIconContainer = $(By.xpath("//div[@class='icon-container']"));
    SelenideElement authButton = $(By.xpath("//p-button//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']"));
    SelenideElement errorMessageHeader = $x("//div[@class='head ng-star-inserted']");
    SelenideElement errorMessageEmptyAuthField = $(By.xpath("//div[@class='message-body']//div[@class='description ng-star-inserted']"));
    SelenideElement errorMessageWithInvalidAuthData = $(By.xpath("//div[@class='message-body']//div[@class='description ng-star-inserted']"));
    SelenideElement accountBalanceHeader = $x("//div[@class='account-balance']/h4");
    SelenideElement moneyBalance = $x("//div[@class='balance-data ng-star-inserted']/div[1]");
    SelenideElement bonusBalance = $x("//div[@class='balance-data ng-star-inserted']/div[2]");
    SelenideElement bonusExpiration = $x("//div[@class='balance-data ng-star-inserted']/div[3]");
    SelenideElement balanceInAuthButton = $x("//p-button//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']");
    SelenideElement refillAccountButton = $x("//div[@class='account-balance']//button[@class='p-ripple p-button p-component']");
    SelenideElement logOutButton = $x("//div[@class='account-balance']//button[@class='p-ripple p-button p-component p-button-outlined']");

    public void authWithCardUid(String cardUid) {
        authButton.click();
        authModalInput.sendKeys(cardUid);
        authModalConfirmButton.click();
    }

    public void authWithEmail(String email) {
        authButton.click();
        authModalInput.sendKeys(email);
        authModalConfirmButton.click();
    }

    public void clickConfirmAuthButtonInModal() {
        authModalConfirmButton.click();
    }

//    public static void clickModalAuthButton() {
//        authModalConfirmButton.click();
//    }

    public void checkDisabledConfirmAuthButtonWithEmptyAuthField() {
        errorMessageEmptyAuthField.shouldBe(Condition.disabled);
    }

    public String getErrorMessageWithInvalidAuthData() {
        return errorMessageWithInvalidAuthData.shouldBe(Condition.visible).getText();
    }

    public String getErrorMessageHeader() {
        return errorMessageHeader.shouldBe(Condition.visible).getText();
    }

    public String getValueInAuthButton() {
        return authButton.getText();
    }

    public int checkMoneyBalance() {
        authButton.click();
        accountBalanceHeader.should(Condition.visible);

        String[] splitWords = moneyBalance.getText().split(" ");

        return Integer.parseInt(splitWords[0]);
    }

    public int checkBonusBalance() {
        authButton.click();
        accountBalanceHeader.should(Condition.visible);

        String[] splitWords = bonusBalance.getText().split(" ");

        return Integer.parseInt(splitWords[0]);
    }

    public int checkExpirationBonusBalance() {
        authButton.click();
        accountBalanceHeader.should(Condition.visible);

        String[] splitWords = bonusExpiration.getText().split(" ");

        return Integer.parseInt(splitWords[0]);
    }

    public String checkExpirationDateBonus() {
        authButton.click();
        accountBalanceHeader.should(Condition.visible);

        String[] splitWords = bonusExpiration.getText().split(" ");

        return splitWords[3];
    }

    public int checkMoneyBalanceInAuthButton() {
        balanceInAuthButton.shouldNotHave(Condition.text("Есть карта?"), Duration.ofSeconds(2));

        String[] splitWords = balanceInAuthButton.getText().split("\\(| ");

        return Integer.parseInt(splitWords[2]);
    }

    public int checkBonusBalanceInAuthButton() {
        balanceInAuthButton.shouldNotHave(Condition.text("Есть карта?"), Duration.ofSeconds(2));

        String[] splitWords = balanceInAuthButton.getText().split("\\(| ");

        return Integer.parseInt(splitWords[5]);
    }

    public void getRefillAccountFromAuthModal() {
        authButton.click();
        refillAccountButton.shouldHave(Condition.text("Хочу пополнить карту")).click();
    }

    public void logout() {
        authButton.click();
        logOutButton.click();
    }




//  authButton.getText().equals(cardUid.trim());

//    public void authWithData() {
//        switch (authIndex) {
//            case 0: authData =;
//        }
//    }
}
