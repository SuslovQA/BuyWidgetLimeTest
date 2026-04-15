package data;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class DataHelper {

    public static class Ticket {
        int index;
        String name;
        double price;

        public Ticket(int index, String name, double price) {
            this.index = index;
            this.name = name;
            this.price = price;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

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
