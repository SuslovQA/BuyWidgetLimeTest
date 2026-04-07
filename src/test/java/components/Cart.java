package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Cart {
    SelenideElement shopCart = $(By.className("shop-cart"));
    SelenideElement shopCartHeader = $x("//div[@class='cart-head']/h4");
    ElementsCollection itemsName = $$x("//div[@class='item-name']");
    SelenideElement buyOrderButton = $x("//p-button[@class='make-order-button']/button");
    ElementsCollection itemsAmount = $$x("//div[@class='items-amount']");

    public String getItemNameInCart() {
        return itemsName.get(0).getText();
    }

    public List<String> getListOfItemsNamesInCart() {
        return itemsName.texts();
    }

    public int getItemAmountInCart(int index) {
        return Integer.parseInt(itemsAmount.get(index).getText());
    }

    public String searchTicketInCartByIndex(int index) {
        return itemsName.get(index).getText();

    }

    // remove ticket
    // remove all tickets
    // + ticket in cart
    // - ticket in cart
    //        shopCart.shouldBe(Condition.exist);
    //        shopCartHeader.shouldBe(Condition.visible).getText().trim().equals("Корзина");
}
