package components;

import com.codeborne.selenide.*;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Cart {
    SelenideElement shopCart = $x("//lime-shop-cart");
    SelenideElement shopCartHeader = $x("//div[@class='cart-head']/h4");
    ElementsCollection itemsName = $$x("//div[@class='item-name']");
    ElementsCollection itemsAmount = $$x("//div[@class='items-amount']");
    SelenideElement clearCart = $x("//button[@class='clear-cart']");
    ElementsCollection deleteAllEqualsItemsButton = $$x("//button[@class='delete-button']");
    ElementsCollection removeOneTicketMinusButton = $$x("//button[@class='sign-button ng-star-inserted'][1]");
    SelenideElement basePriceItem = $x("//div[@class='base-price']");
    SelenideElement discountPrice = $x("//div[@class='discount-price']");
    SelenideElement makeOrderButton = $x("//p-button[@class='make-order-button']/button");
    SelenideElement promoHeader = $x("//p-accordion-header");
    SelenideElement promoInput = $x("//input[@class='p-inputtext p-component promo-input ng-untouched ng-pristine ng-valid']");
    SelenideElement confirmPromo = $x("//div[@class='tick-addon']");
    SelenideElement discountSumTitleInTotal = $x("//div[@class='discount-sum ng-star-inserted']/div[@class='sum-title']");
    SelenideElement discountSumAmountInTotal = $x("//div[@class='discount-sum ng-star-inserted']/div[@class='sum-amount']");
    SelenideElement baseSumTitleInTotal = $x("//div[@class='base-sum ng-star-inserted']/div[@class='sum-title']");
    SelenideElement baseSumAmountInTotal = $x("//div[@class='base-sum ng-star-inserted']/div[@class='sum-amount']");
    SelenideElement sumTitleInTotal = $x("//div[@class='final-sum']/div[@class='sum-title']");
    SelenideElement sumAmountInTotal = $x("//div[@class='final-sum']/div[@class='sum-amount']");
    SelenideElement infoMessage = $x("//div[@class='head ng-star-inserted']");

    public String getItemNameInCart() {
        shopCart.shouldBe(visible);
        shopCartHeader.shouldHave(Condition.text("Корзина"));

        return itemsName.get(0).getText();
    }

    public List<String> getListOfItemsNamesInCart() {
        shopCart.shouldBe(visible);
        itemsName.forEach(x -> x.shouldBe(visible));

        return itemsName.texts();
    }

    public int getItemAmountInCart(int index) {
        return Integer.parseInt(itemsAmount.get(index).getText());
    }

    public void clearCart() {
        clearCart.shouldBe(Condition.text("Очистить все")).click();
        shopCartHeader.shouldNotBe(visible);
        infoMessage.shouldBe(visible).shouldHave(Condition.text("Корзина очищена"));
    }

    public void removeAllEqualsTicketsInCartByIndex(int indexTicketInCart) {
        deleteAllEqualsItemsButton.get(indexTicketInCart).click();
    }

    public void removeOneTicketFromCartByIndex(int indexTicketInCart) {
        removeOneTicketMinusButton.get(indexTicketInCart).click();
        infoMessage.shouldHave(Condition.exactText("Товар удалён"));
    }

    public boolean checkRemovingCart() {
        return !shopCartHeader.isDisplayed();
    }

    public boolean checkRemovingTicketFromCartByTicketName(String nameTicket) {
        shopCart.shouldBe(visible);

        return !itemsName.texts().contains(nameTicket);
    }

    public OrderPayment makeOrder() {
        makeOrderButton.shouldBe(Condition.text("Оформить заказ")).click();

        return new OrderPayment();
    }

    public Cart applyDiscount(String promoCode) {
        promoHeader.shouldBe(visible);
        promoInput.sendKeys(promoCode);
        confirmPromo.click();
        discountSumTitleInTotal.shouldBe(visible);

        return this;
    }

    public boolean checkApplyingTenPercentDiscount() {
        basePriceItem.shouldHave(Condition.cssValue("text-decoration", "line-through"));
        discountPrice.shouldHave(Condition.cssValue("color", "rgba(249, 91, 28, 1)"));
        baseSumTitleInTotal.shouldHave(Condition.text("Стоимость"));
        discountSumTitleInTotal.shouldHave(Condition.text("Скидка"));
        sumTitleInTotal.shouldHave(Condition.text("Итого"));

        String[] spiltDiscountWords = discountPrice.getText().split(" ");
        String[] spiltBasePriceWords = basePriceItem.getText().split(" ");
        String[] spiltBasePriceWordsInTotal = baseSumAmountInTotal.getText().split(" ");
        String[] spiltSumAmountWordsInTotal = sumAmountInTotal.getText().split(" ");
        String[] spiltDiscountSumAmountWordsInTotal = discountSumAmountInTotal.getText().split(" ");

        double stringToDoubleDiscount = Double.parseDouble(spiltDiscountWords[0]);
        double stringToDoubleBasePrice = Double.parseDouble(spiltBasePriceWords[0]);
        double stringToDoubleBasePriceInTotal = Double.parseDouble(spiltBasePriceWordsInTotal[0]);
        double stringToDoubleSumAmountInTotal = Double.parseDouble(spiltSumAmountWordsInTotal[0]);
        double stringToDoubleDiscountSumAmountInTotal = Double.parseDouble(spiltDiscountSumAmountWordsInTotal[0]);

        double resultInCartItem = stringToDoubleBasePrice * 0.9;
        double resultInTotal = stringToDoubleBasePriceInTotal - stringToDoubleSumAmountInTotal;

        return resultInCartItem == stringToDoubleDiscount && resultInTotal == stringToDoubleDiscountSumAmountInTotal * -1;
    }
}
