package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {
    SelenideElement shopCart = $(By.className("shop-cart"));
    SelenideElement itemName = $x("div[class='item-name']");

    public Cart() {
        shopCart.shouldBe(Condition.exist);
    }

    public void purchaseOrder() {

    }
}
