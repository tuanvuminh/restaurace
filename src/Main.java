import com.*;
import com.exceptions.OrderException;
import com.settings.Settings;

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
        cookBook.addDish(dish3);
        cookBook.addDish(dish4);
        cookBook.getCookbook();

        menu.addDish(dish1);
        menu.addDish(dish2);
        menu.addDish(dish3);
        menu.getMenu();

        Waiter adam = new Waiter(1);
        Waiter erik = new Waiter(2);
        Table table1 = new Table(5);
        Table table2 = new Table(10);

        try {
            menu.saveToFile(Settings.filename(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru!" + e.getLocalizedMessage());
        }

        Order firstOrder = new Order(table1, adam, dish1, LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 15, 15));
        Order secondOrder = new Order(table2, adam, dish2, LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 18, 20));
        restaurantManager.addOrder(firstOrder);
        restaurantManager.addOrder(secondOrder);
        firstOrder.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 15, 45));
        secondOrder.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 18, 50));

        try {
            System.out.println(restaurantManager.averageTimeOfOrdersToComplete());
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        restaurantManager.getOrderedDishesOfToday();
        restaurantManager.getOrdersPerTable(table1);
        restaurantManager.getOrdersPerTable(table2);


    }
}