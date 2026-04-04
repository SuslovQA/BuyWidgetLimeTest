package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart {
    SelenideElement shopCart = $(By.className("shop-cart"));
    SelenideElement shopCartHeader = $x("//div[@class='cart-head']/h4");
    SelenideElement itemName = $x("div[class='item-name']");
    SelenideElement buyOrderButton = $x("//p-button[@class='make-order-button']/button");

    public Cart() {
        shopCart.shouldBe(Condition.exist);
        shopCartHeader.shouldBe(Condition.visible).getText().trim().equals("Корзина");
    }

    public void purchaseOrder() {
//        itemName.getText().equals(Tickets.getTicketName(0));
        buyOrderButton.click();
    }
}
