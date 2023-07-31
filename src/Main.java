import com.restaurant.*;
import com.restaurant.exceptions.OrderException;
import com.restaurant.settings.Settings;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws OrderException {

        // 1. Testovací scénář
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

        // 2. Připrav testovací data. Vlož do systému 3 jídla.
        Dish dish1 = new Dish("Kuřecí řízek obalovaný 150 g", BigDecimal.valueOf(130), 30, Category.MAIN, "kureci-rizek-01", "kureci-rizek-09"
        );
        Dish dish2 = new Dish("Hranolky 150g", BigDecimal.valueOf(50), 15, Category.MAIN
        );
        Dish dish3 = new Dish("Pstruh na víně 200 g", BigDecimal.valueOf(200), 35, Category.MAIN, "pstruh-na-vine-01"
        );

        // Odebrání fotky
        dish1.removeMainImage();
        dish1.removeOtherImage();

        // Přidání fotky
        dish1.addOtherImage("kureci-rizek-01");
        dish3.addMainImage("pstruh-na-vine-05");
        dish3.addOtherImage("pstruh-na-vine-06");

        cookBook.addDish(dish1);
        cookBook.addDish(dish2);
        cookBook.addDish(dish3);
        cookBook.printCookbook();

        // První a třetí jídlo zařaď do aktuálního menu, druhé jídlo nikoli.
        System.out.println();
        menu.addDish(dish1);
        menu.addDish(dish3);
        menu.printMenu();

        // Vytvoř alespoň tři objednávky pro stůj číslo 15 a jednu pro stůj číslo 2. Objednávky řeší alespoň dva různí číšníci.
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
        restaurantManager.addOrder(order3);
        restaurantManager.addOrder(order4);

        // Min. dvě objednávky jsou již uzavřené, minimálně dvě ještě nikoli.
        order1.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 11, 45)
        );
        order2.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 12, 45)
        );

        // Test uložení neuzavřených objednávek do souboru
        try {
            restaurantManager.saveToFile(Settings.filename1(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());
        }

        // 3. Vyzkoušej přidat objednávku jídla, které není v menu — aplikace musí ohlásit chybu.
        Order testOfOrderingADishWhichIsNotOnMenu = new Order(table15, erik, dish2, LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 15, 00)
        );
        restaurantManager.addOrder(testOfOrderingADishWhichIsNotOnMenu);

        // 4. Uzavření objednávek
        order3.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 16, 15)
        );
        order4.setFulfilmentTime(LocalDateTime.of(2023, 7, LocalDateTime.now().getDayOfMonth(), 16, 00)
        );

        try {
            restaurantManager.saveToFile(Settings.filename1(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());
        }

        // 5. Požadované výstupy
        // 5.1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
        System.out.println();
        System.out.println(restaurantManager.getNumberOfUncompletedOrders());

        // 5.2 Řazení objednávek
        System.out.println();
        System.out.println("Řazení objednávek podle času zadání:");
        restaurantManager.sortByTimeOfOrder();
        restaurantManager.getOrders().forEach(System.out::println);
        System.out.println();
        System.out.println("Řazení objednávek podle ID čišníků:");
        restaurantManager.sortByWaiterId();
        restaurantManager.getOrders().forEach(System.out::println);

        // 5.3 Celková cena objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
        System.out.println();
        System.out.println(restaurantManager.getInfoOfOrdersPerWaiter(adam));
        System.out.println(restaurantManager.getInfoOfOrdersPerWaiter(erik));

        // 5.4 Průměrná doba zpracování objednávek, které byly zadány v určitém časovém období.
        System.out.println();

        try {
            System.out.println(restaurantManager.averageTimeOfOrdersToComplete());
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // 5.5 Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
        System.out.println();
        restaurantManager.getOrderedDishesOfToday();

        // 5.6 Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku):
        System.out.println();

        try {
            restaurantManager.getOrdersPerTable(table15);
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // 6. Změněná data ulož na disk. Po spuštění aplikace musí být data opět v pořádku načtena.
        try {
            restaurantManager.saveToFile(Settings.filename1(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());
        }

        try {
            menu.saveToFile(Settings.filename2(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());
        }

        try {
            cookBook.saveToFile(Settings.filename3(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu do souboru! " + e.getLocalizedMessage());
        }

        // 7. Připrav do složky projektu poškozený vstupní soubor/poškozené vstupní soubory, které se nepodaří načíst.
        //    Aplikace se při spuštění s těmito soubory musí zachovat korektně — nesmí spadnout..
        try {
            menu.loadFromFile(Settings.filename5(), Settings.delimiter());
        } catch (OrderException e) {
            System.err.println("Chyba při zápisu ze souboru! " + e.getLocalizedMessage());
        }
    }
}