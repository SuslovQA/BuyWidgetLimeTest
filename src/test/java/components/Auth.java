package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Auth {
    static SelenideElement authModalInput = $(By.xpath("//input[@class='p-inputtext p-component auth-input']"));
    static SelenideElement authModalConfirmButton = $(By.xpath("//div[@class='confirm-button-container']//button"));
    SelenideElement authIconContainer = $(By.xpath("//div[@class='icon-container']"));
    SelenideElement authButton = $(By.xpath("//p-button//button[@class='p-ripple p-button p-component p-button-outlined p-button-lg']"));
    SelenideElement errorMessageEmptyAuthField = $(By.xpath("//div[@class='message-body']//div[@class='description ng-star-inserted']"));
    SelenideElement errorMessageWithInvalidAuthData = $(By.xpath("//div[@class='message-body']//div[@class='description ng-star-inserted']"));
    SelenideElement accountBalanceHeader = $x("//div[@class='account-balance']/h4");
    SelenideElement moneyBalance = $x("//div[@class='balance-data']/div[1]");
    SelenideElement bonusBalance = $x("//div[@class='balance-data']/div[2]");
    SelenideElement bonusExpiration = $x("//div[@class='balance-data']/div[3]");
    SelenideElement refillAccountButton = $x("//div[@class='account-balance']//button[@class='p-ripple p-button p-component']");
    SelenideElement logOutButton = $x("//div[@class='account-balance']//button[@class='p-ripple p-button p-component p-button-outlined']");

    public Auth authWithCardUid(String cardUid) {
        authButton.click();
        authModalInput.sendKeys(cardUid);
        authModalConfirmButton.click();

        return this;
    }

    public Auth authWithEmail(String email) {
        authButton.click();
        authModalInput.sendKeys(email);
        authModalConfirmButton.click();

        return this;
    }

    public Auth clickConfirmAuthButtonInModal() {
        authModalConfirmButton.click();

        return this;
    }

//    public static void clickModalAuthButton() {
//        authModalConfirmButton.click();
//    }

    public String getErrorMessageEmptyAuthField() {
        return errorMessageEmptyAuthField.shouldBe(Condition.visible).getText();
    }

    public String getErrorMessageWithInvalidAuthData() {
        return errorMessageWithInvalidAuthData.shouldBe(Condition.visible).getText();
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
