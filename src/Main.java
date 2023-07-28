import com.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        RestaurantManager restaurantManager = new RestaurantManager();
        CookBook cookBook = new CookBook();
        Menu menu = new Menu();

        Dish dish1 = new Dish("Rigatoni s rajčaty a sýrem stracciatella", BigDecimal.valueOf(275),
                30, Category.MAIN, "rigatoni-s-rajcaty-syr-stracciatella-01"
        );
        Dish dish2 = new Dish("Tagliata z hovězí svíčkové na litinové pánvi s rozmarýnem a zeleným pepřem", BigDecimal.valueOf(498),
                45, Category.MAIN, "tagliata-z-hovezi-svickove-01"
        );
        Dish dish3 = new Dish("Variace zmrzlin", BigDecimal.valueOf(145),
                10, Category.DESSERT, "variace-zmrzlin-01"
        );
        Dish dish4 = new Dish("Tatarák se zauzenou majonézou a fenyklem", BigDecimal.valueOf(255),
                15, Category.STARTER, "tatarak-majoneza-fenykl-01"
        );

        cookBook.addDish(dish1);
        cookBook.addDish(dish2);
        cookBook.addDish(dish4);
        cookBook.getCookbook();

        menu.addDish(dish1);
        menu.addDish(dish2);
        menu.addDish(dish4);
        menu.getMenu();

       try {
           menu.saveToFile(Settings.filename(), Settings.delimiter());
       } catch (OrderException e) {
           System.err.println("Chyba při zápisu do souboru!" +e.getLocalizedMessage());
       }


    }
}