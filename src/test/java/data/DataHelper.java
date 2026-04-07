package data;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;
import components.Tickets;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

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
}
