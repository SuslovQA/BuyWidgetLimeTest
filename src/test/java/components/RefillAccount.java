package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RefillAccount {
    SelenideElement refillAccountHeader = $x("//lime-refill-sums//h3");
    SelenideElement refillAccountSubTitle = $x("//lime-refill-sums//p");
    ElementsCollection buttonsOfSumsList = $$x("//div[@class='amounts-list ng-star-inserted']/button");
    ElementsCollection amountSumsList = $$x("//div[@class='amounts-list ng-star-inserted']/button/div");
    SelenideElement sumInCart = $x("//div[@class='base-price']");
    SelenideElement sumInput = $x("//input[@class='p-inputtext p-component p-inputnumber-input']");
    SelenideElement addSumInCartButton = $x("//div[@class='refill-controller']//button");

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

        sumInCart.shouldBe(Condition.exactText(amountSumsList.get(sumButtonIndex).getText()));
    }

    public String getAddedSumInCart() {
        return sumInCart.getText();
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
            if (splitCurrentBalance[2].equals("1") || splitCurrentBalance[2].equals("2") || splitCurrentBalance[2].equals("3")) {
                result = splitCurrentBalance[2] + splitCurrentBalance[3];
            }
        }

        int result1 = Integer.parseInt(result) + Integer.parseInt(splitAddedSum[0]);

        return String.valueOf(result1);
    }
}
