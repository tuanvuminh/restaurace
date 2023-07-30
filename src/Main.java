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
        Dish dish1 = new Dish("Kuřecí řízek obalovaný 150 g", BigDecimal.valueOf(130), 30, Category.MAIN, "kureci-rizek-01"
        );
        Dish dish2 = new Dish("Hranolky 150g", BigDecimal.valueOf(50), 15, Category.MAIN
        );
        Dish dish3 = new Dish("Pstruh na víně 200 g", BigDecimal.valueOf(200), 35, Category.MAIN, "pstruh-na-vine-01"
        );

        cookBook.addDish(dish1);
        cookBook.addDish(dish2);
        cookBook.addDish(dish3);
        cookBook.printCookbook();

        System.out.println();
        menu.addDish(dish1);
        menu.addDish(dish3);
        menu.printMenu();

        Table table15 = new Table(15);
        Table table2 = new Table(2);

        Waiter adam = new Waiter(1);
        Waiter erik = new Waiter(2);

        Order order1 = new Order(table15, adam, dish1, LocalDateTime.of(2023, 7,
                LocalDateTime.now().getDayOfMonth(), 11, 00)
        );
        Order order2 = new Order(table15, adam, dish1, LocalDateTime.of(2023, 7,
                LocalDateTime.now().getDayOfMonth(), 12, 00)
        );
        Order order3 = new Order(table15, erik, dish3, 3, LocalDateTime.of(2023, 7,
                LocalDateTime.now().getDayOfMonth(), 15, 00)
        );
        Order order4 = new Order(table2, adam, dish3, 2, LocalDateTime.of(2023, 7,
                LocalDateTime.now().getDayOfMonth(), 15, 00)
        );

        restaurantManager.addOrder(order1);
        restaurantManager.addOrder(order2);

        order1.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 11, 45)
        );
        order2.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 12, 40)
        );

        // 3.
        Order testOfOrderingADishWhichIsNotOnMenu = new Order(table15, erik, dish2, LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 15, 00)
        );
        restaurantManager.addOrder(testOfOrderingADishWhichIsNotOnMenu);

        // 4.
        try {
            restaurantManager.saveToFile(Settings.filename1(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru!" + e.getLocalizedMessage());
        }

        // 5. Požadované výstupy
        // 5.1.
        System.out.println();
        System.out.println(restaurantManager.getNumberOfUncompletedOrders());

        // 5.2

        // 5.3
        System.out.println(restaurantManager.getInfoOfOrdersPerWaiter(adam));
        System.out.println(restaurantManager.getInfoOfOrdersPerWaiter(erik));

        // 5.4
        System.out.println();
        try {
            System.out.println(restaurantManager.averageTimeOfOrdersToComplete());
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // 5.5
        System.out.println();
        restaurantManager.getOrderedDishesOfToday();

        // 5.6
        System.out.println();
        restaurantManager.getOrdersPerTable(table15);

        // 6.

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

    }
}