import com.*;
import com.exceptions.OrderException;
import com.settings.Settings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Testovací scénář

        // 1.
        RestaurantManager restaurantManager = new RestaurantManager();
        CookBook cookBook = new CookBook();
        Menu menu = new Menu();

        try {
            restaurantManager.saveToFile(Settings.filename1(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru!" + e.getLocalizedMessage());
        }

        try {
            menu.saveToFile(Settings.filename2(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru!" + e.getLocalizedMessage());
        }

        try {
            cookBook.saveToFile(Settings.filename3(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru!" + e.getLocalizedMessage());
        }

        // 2.
        Dish dish1 = n


    }
}