package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Cart {
    SelenideElement shopCart = $(By.className("shop-cart"));
    SelenideElement shopCartHeader = $x("//div[@class='cart-head']/h4");
    ElementsCollection itemsName = $$x("//div[@class='item-name']");
    ElementsCollection itemsAmount = $$x("//div[@class='items-amount']");
    SelenideElement clearCart = $x("//button[@class='clear-cart']");
    SelenideElement makeOrderButton = $x("//p-button[@class='make-order-button']/button");

    public String getItemNameInCart() {
        return itemsName.get(0).getText();
    }

    public List<String> getListOfItemsNamesInCart() {
        return itemsName.texts();
    }

    public int getItemAmountInCart(int index) {
        return Integer.parseInt(itemsAmount.get(index).getText());
    }

    public void clearCart() {
        clearCart.shouldBe(Condition.text("Очистить все")) .click();
    }

    public void makeOrder() {
        makeOrderButton.shouldBe(Condition.text("оформить заказ")).click();
    }

//    public String searchTicketInCartByIndex(int index) {
//        return itemsName.get(index).getText();
//
//    }

    // remove ticket
    // remove all tickets
    // + ticket in cart
    // - ticket in cart
    //        shopCart.shouldBe(Condition.exist);
    //        shopCartHeader.shouldBe(Condition.visible).getText().trim().equals("Корзина");
}
