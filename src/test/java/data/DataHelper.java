package data;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class DataHelper {

    public static boolean isModalDisplayedAfterClick(SelenideElement element, Duration timeout) {
        try {
            // Пытаемся найти элемент и дождаться его видимости
            element.shouldBe(Condition.visible, timeout);
            return true; // Элемент появился и виден
        } catch (AssertionError e) {
            // Таймаут истек, элемент так и не появился
            return false;
        }
    }

}
