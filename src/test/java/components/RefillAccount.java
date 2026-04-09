package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class RefillAccount {
    SelenideElement refillAccountHeader = $x("//lime-refill-sums//h3");
    SelenideElement refillAccountSubTitle = $x("//lime-refill-sums//p");
    ElementsCollection amountList = $$x("//div[@class='amounts-list ng-star-inserted']/button");
    ElementsCollection amountSumsList = $$x("//div[@class='amounts-list ng-star-inserted']/button/div");

    SelenideElement refillInput = $x("//p-input-number/input");

    public String getRefillAccountHeader() {
        return refillAccountHeader.getText();
    }

    public String getRefillAccountSubTitle() {
        return refillAccountSubTitle.getText();
    }

    public List<String> getAmountSums() {
        return amountSumsList.texts();
    }
}
