/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.testing.Account;
import com.mycompany.testing.Address;
import com.mycompany.testing.Complain;
import com.mycompany.testing.Delivery;
import com.mycompany.testing.FileHandler;
import com.mycompany.testing.Menu;
import com.mycompany.testing.Notification;
import com.mycompany.testing.Order;
import com.mycompany.testing.OrderItem;
import com.mycompany.testing.Review;
import com.mycompany.testing.Transaction;
import com.mycompany.testing.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *users.removeIf(user -> user.name.equals("Alice"));
 * @author georg
 */
public class Testing {

    public static void main(String[] args) {
        /*String filePath = "users.txt";
        FileHandler<User> userFileHandler = new FileHandler<>();
        // 创建一些用户
        //List<User> users = new ArrayList<>();
        Type userType = new TypeToken<User>(){}.getType();
        List<User> users = userFileHandler.readTxt(filePath, userType);
        System.out.println("读取到的用户: " + users.size());
        users.removeIf(user -> user.name.equals("Alice"));
        userFileHandler.writeTxt(filePath, users);
        System.out.println("读取到的用户: " + users.size());*/
        
         
        
        //folder path
        
        String userFilePath = "users.txt";
        String accountFilePath = "accounts.txt";
        String menuFilePath = "menus.txt";
        String orderFilePath = "orders.txt";
        String orderItemFilePath = "order_items.txt";
        String deliveryFilePath = "delivery.txt";
        String transactionFilePath = "transactions.txt";
        String notificationFilePath = "notifications.txt";
        String reviewFilePath = "reviews.txt";
        String complainFilePath = "complains.txt";

        //declare type
        Type userType = new TypeToken<User>() {}.getType();
        Type accountType = new TypeToken<Account>() {}.getType();
        Type menuType = new TypeToken<Menu>() {}.getType();
        Type orderType = new TypeToken<Order>() {}.getType();
        Type orderItemType = new TypeToken<OrderItem>() {}.getType();
        Type deliveryType = new TypeToken<Delivery>() {}.getType();
        Type transactionType = new TypeToken<Transaction>() {}.getType();
        Type notificationType = new TypeToken<Notification>() {}.getType();
        Type reviewType = new TypeToken<Review>() {}.getType();
        Type complainType = new TypeToken<Complain>() {}.getType();

        //这个是开每一个的file
        FileHandler<User> userFileHandler = new FileHandler<>();
        FileHandler<Account> accountFileHandler = new FileHandler<>();
        FileHandler<Menu> menuFileHandler = new FileHandler<>();
        FileHandler<Order> orderFileHandler = new FileHandler<>();
        FileHandler<Delivery> deliveryFileHandler = new FileHandler<>();
        FileHandler<Transaction> transactionFileHandler = new FileHandler<>();
        FileHandler<Notification> notificationFileHandler = new FileHandler<>();
        FileHandler<Review> reviewFileHandler = new FileHandler<>();
        FileHandler<Complain> complainFileHandler = new FileHandler<>();
         

        List<User> users = new ArrayList<>();
        users.add(new User("C001", "john_doe", "password123", "Customer", "0123456789", new Address("10", "Jalan Bukit Bintang", "Taman Bintang", "55100", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("C002", "jane_smith", "pass456", "Customer", "0129876543", new Address("15", "Jalan Tun Razak", "Taman Tun", "50450", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("C003", "charlie_lim", "custpass123", "Customer", "0125566778", new Address("30", "Jalan Sultan Ismail", "Taman Sultan", "50250", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("C004", "diana_tan", "custpass456", "Customer", "0126677889", new Address("35", "Jalan Pudu", "Taman Pudu", "55100", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("C005", "elizabeth_ong", "custpass789", "Customer", "0127788990", new Address("40", "Jalan Imbi", "Taman Imbi", "55100", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("C006", "frank_yeoh", "custpass012", "Customer", "0128899001", new Address("45", "Jalan Raja Chulan", "Taman Raja", "50200", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));

        users.add(new User("V001", "vendor_a", "vendorpass", "Vendor", "0131122334", new Address("20", "Jalan Ampang", "Taman Ampang", "50400", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        users.add(new User("V002", "bob_lee", "vendorpass012", "Vendor", "0133344556", new Address("25", "Jalan Klang Lama", "Taman Seputeh", "58100", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));

        users.add(new User("A001", "admin_001", "adminpass", "Administrator", "0142233445", new Address("50", "Jalan Raja Laut", "Taman Raja", "50350", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));

        users.add(new User("D001", "runner_001", "runnerpass", "DeliveryRunner", "0153344556", new Address("55", "Jalan Kuching", "Taman Kuching", "51200", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        
        users.add(new User("M001", "manager_001", "managerpass", "Manager", "0164455667", new Address("60", "Jalan Damansara", "Taman Damansara", "60000", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        userFileHandler.writeTxt(userFilePath, users);

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("A0001", "C001", 100.0));
        accounts.add(new Account("A0002", "C002", 50.0)); 
        accounts.add(new Account("A0003", "C003", 120.0));
        accounts.add(new Account("A0004", "C004", 5.0));
        accounts.add(new Account("A0005", "C005", 18.0));
        accounts.add(new Account("A0006", "C006", 21.0));

        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("V001", "M0001", "Burger", 12.5));
        menus.add(new Menu("V001", "M0002", "Pizza", 20.0));
        menus.add(new Menu("V001", "M0003", "Fries", 5.0));
        menus.add(new Menu("V002", "M0004", "Sky Juice", 1.0));
        menus.add(new Menu("V002", "M0005", "Milo Ice", 3.0));
        menus.add(new Menu("V002", "M0006", "Teh Ice", 3.0));
        
        List<Order> orders = new ArrayList<>();
        ArrayList<OrderItem> items1 = new ArrayList<>();ArrayList<OrderItem> items2 = new ArrayList<>();ArrayList<OrderItem> items3 = new ArrayList<>();ArrayList<OrderItem> items4 = new ArrayList<>();
        items1.add(new OrderItem("M0001", "Burger", 2, 12.5));
        items1.add(new OrderItem("M0003", "Fries", 1, 5.0));
        orders.add(new Order("O0001", "C001", "V001", items1, "Delivery", 4.0, 30.0, "Accepted", new Address("123", "Main St", "Taman Main", "12345", "Kuala Lumpur", "Federal Territory of Kuala Lumpur"), "D0001", LocalDateTime.of(2023, 1, 1, 10, 0)));
        items2.add(new OrderItem("M0002", "Pizza", 2, 20.0));
        items2.add(new OrderItem("M0003", "Fries", 1, 5.0));
        orders.add(new Order("O0002", "C001", "V001", items2, "Takeaway", 0.0, 45.0, "Pending", null, null));
        items3.add(new OrderItem("M0001", "Burger", 1, 12.5));
        orders.add(new Order("O0003", "C001", "V001", items3, "Dine-in", 0.0, 20.0, "Completed", null, null));
        items4.add(new OrderItem("M0002", "Pizza", 1, 20.0));
        items4.add(new OrderItem("M0001", "Burger", 1, 12.5)); 
        items4.add(new OrderItem("M0003", "Fries", 2, 5.0));
        orders.add(new Order("O0004", "C002", "V001", items4, "Delivery", 4.0, 25.0, "Accepted", new Address("456", "Elm St", "Taman Elm", "54321", "Kuala Lumpur", "Federal Territory of Kuala Lumpur"), "D0001", LocalDateTime.of(2023, 1, 1, 10, 0)));
        /*ArrayList<OrderItem> items5 = new ArrayList<>();
        ArrayList<OrderItem> items6 = new ArrayList<>();
        items5.add(new OrderItem("M0004", "Sky Juice", 2, 1.0));
        items5.add(new OrderItem("M0005", "Milo Ice", 1, 3.0));
        orders.add(new Order("O0005", "C003", "V002", items5, "Dine-in", 0.0, 10.0, "Completed", null, null));
        items6.add(new OrderItem("M0006", "Teh Ice", 3, 3.0));
        orders.add(new Order("O0006", "C004", "V002", items6, "Dine-in", 0.0, 9.0, "Pending", null, null));
        /*
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem("O0001", "M0001", "Burger", 2, 12.5));
        orderItems.add(new OrderItem("O0001", "M0003", "Fries", 1, 5.0));
        orderItems.add(new OrderItem("O0002", "M0002", "Pizza", 2, 20.0));
        orderItems.add(new OrderItem("O0002", "M0003", "Fries", 1, 5.0));
        orderItems.add(new OrderItem("O0003", "M0001", "Burger", 1, 12.5));
        orderItems.add(new OrderItem("O0003", "M0002", "Pizza", 1, 20.0));
        orderItems.add(new OrderItem("O0004", "M0001", "Burger", 1, 12.5)); 
        orderItems.add(new OrderItem("O0004", "M0003", "Fries", 2, 5.0));  
        */
        
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(new Delivery("D0001", "O0001", "D001", new Address("123", "Main St", "Taman Main", "12345", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        deliveries.add(new Delivery("D0002", "O0004", "D001", new Address("456", "Elm St", "Taman Elm", "54321", "Kuala Lumpur", "Federal Territory of Kuala Lumpur")));
        
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T0001", "C001", 50.0, 50));
        transactions.add(new Transaction("T0002", "C001", 50.0, 100));
        transactions.add(new Transaction("T0003", "C002", 50.0, 50,LocalDateTime.of(2023, 1, 1, 10, 0)));
        
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("N0001", "C001", "Your order O0001 has been accepted"));
        notifications.add(new Notification("N0002", "V001", "New order O0001 received"));
        notifications.add(new Notification("N0003", "C002", "Your order O0004 has been accepted"));
 
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("R0001", "C001", "F", "M0001", 5, "Good"));
        reviews.add(new Review("R0002", "C001", "F", "M0003", 3, "Okay"));
        reviews.add(new Review("R0003", "C001", "D", "D001", 4, "Fast delivery"));

        List<Complain> complains = new ArrayList<>();
        complains.add(new Complain("C0001", "C001", "O0001", "Food was cold", "Pending"));
        complains.add(new Complain("C0002", "C001", "O0003", "Delivery was late", "Resolved"));
        
        userFileHandler.writeTxt(userFilePath, users);
        accountFileHandler.writeTxt(accountFilePath, accounts);
        menuFileHandler.writeTxt(menuFilePath, menus);
        orderFileHandler.writeTxt(orderFilePath, orders);
        //orderItemFileHandler.writeTxt(orderItemFilePath, orderItems);
        deliveryFileHandler.writeTxt(deliveryFilePath, deliveries);
        transactionFileHandler.writeTxt(transactionFilePath, transactions);
        notificationFileHandler.writeTxt(notificationFilePath, notifications);
        reviewFileHandler.writeTxt(reviewFilePath, reviews);
        complainFileHandler.writeTxt(complainFilePath, complains);

        //new HomePage().setVisible(true);
    }
}
