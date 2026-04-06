package data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import components.Tickets;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {
    static ElementsCollection ticketsInCategory = $$(By.xpath("//button[@class='sign-button ng-star-inserted']"));


    public static void addFirstTicketFromCategory() {
        $(By.xpath("//lime-card//button")).click();
             ticketsInCategory.get(0).click();
    }
}
