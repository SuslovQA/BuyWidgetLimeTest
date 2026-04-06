package data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import components.Tickets;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class DataHelper {
    public static SelenideElement virtualProcessing = $x("//div[contains(text(), ' Онлайн виртуальный ')]/parent::lime-processing-icon/parent::button");
}
