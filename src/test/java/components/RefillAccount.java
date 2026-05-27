package components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataCards;
import data.DataHelper;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RefillAccount {
    SelenideElement refillAccountHeader = $x("//lime-refill-sums//h3");
    SelenideElement refillAccountSubTitle = $x("//lime-refill-sums//p");
    ElementsCollection buttonsOfSumsList = $$x("//div[@class='amounts-list ng-star-inserted']/button");
    ElementsCollection amountSumsList = $$x("//div[@class='amounts-list ng-star-inserted']/button/div[@class='sum-amount']");
    SelenideElement sumInCart = $x("//div[@class='base-price']");
    SelenideElement sumInput = $x("//input[@class='p-inputtext p-component p-inputnumber-input']");
    SelenideElement addSumInCartButton = $x("//div[@class='refill-controller']//button");
    ElementsCollection bonusAmountInSumButton = $$x("//div[@class='bonus-sum ng-star-inserted']");
    ElementsCollection bonusIconInSumButton = $$x("//*[local-name()='svg' and @height='20']");

    public String getRefillAccountHeader() {
        return refillAccountHeader.getText();
    }

    public String getRefillAccountSubTitle() {
        return refillAccountSubTitle.getText();
    }

    public List<String> getAmountSumsList() {
        return amountSumsList.texts();
    }

    public void AddSumFromButtonsSumsByIndex(int sumButtonIndex) {
        buttonsOfSumsList.get(sumButtonIndex).click();

        if (Auth.authModalConfirmButton.exists()) {
            Auth.authModalConfirmButton.click();

            Auth.authModalConfirmButton.shouldNotBe(visible);
        }

        sumInCart.shouldBe(Condition.exactText(DataHelper.replaceUnicodeSpaceCharacterToSpace(amountSumsList.get(sumButtonIndex).getText())));
    }

    public String getAddedSumInCart() {
        return DataHelper.replaceUnicodeSpaceCharacterToSpace(sumInCart.getText());
    }

    public String getAddedSumInCartWithTrim() {
        return sumInCart.getText().replace('₽', ' ').replace(" ", "");
    }

    public void addSumFromInput(String sum) {
        sumInput.scrollIntoView(false).sendKeys(sum);
        addSumInCartButton.click();
    }

    public String getTotalBalanceAfterRefilling(String currentBalance, String addedSum) {
        String result = "";

        String replaceWords = currentBalance.replace('(', ' ');

        String[] splitCurrentBalance = replaceWords.split(" ");


        String[] splitAddedSum = addedSum.split(" ");

        result = splitCurrentBalance[0];

        if (splitCurrentBalance.length > 2) {
            if (splitCurrentBalance[2].equals("1") || splitCurrentBalance[2].equals("2") || splitCurrentBalance[2].equals("3") || splitCurrentBalance[2].equals("4") || splitCurrentBalance[2].equals("5")) {
                result = splitCurrentBalance[2] + splitCurrentBalance[3];
            }
        }

        int totalResult = Integer.parseInt(result) + Integer.parseInt(splitAddedSum[0]);

        return String.valueOf(totalResult);
    }

    public boolean checkBonusAmountInSumButtons(DataCards card) {
        if (card.getCategory().equals(DataCards.VALID_CARD_UID.getCategory())) {
            bonusAmountInSumButton.get(0).shouldHave(exactText("+ 20 бонусов"));
            bonusIconInSumButton.get(0).shouldBe(visible);
            return true;
        } else if (card.getCategory().equals(DataCards.VALID_CHILD_CARD_UID.getCategory())) {
            bonusAmountInSumButton.shouldHave(CollectionCondition.size(2));
            bonusAmountInSumButton.get(0).shouldHave(exactText("+ 20 бонусов"));
            bonusAmountInSumButton.get(1).shouldHave(exactText("+ 5 бонусов"));
            bonusIconInSumButton.get(0).shouldBe(visible);
            bonusIconInSumButton.get(1).shouldBe(visible);
            return true;
        } else return false;
    }
}
