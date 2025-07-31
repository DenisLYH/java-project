/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mycompany.testing.Delivery;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Delivery Runner Functions
 */
public class DeliveryRunnerFunctions {

    public DeliveryRunnerFunctions() {
    }
    private final String userFilePath = "users.txt";
    private final String orderFilePath = "orders.txt";
    private final String deliveryFilePath = "delivery.txt";
    private final String reviewFilePath = "reviews.txt";
    private final String notificationFilePath = "notifications.txt";

    
    private FileHandler<Order> orderFileHandler = new FileHandler<>();
    private FileHandler<Delivery> deliveryFileHandler = new FileHandler<>();
    private FileHandler<Review> reviewFileHandler = new FileHandler<>();
    private FileHandler<User> usersFileHandler = new FileHandler<>();
    private FileHandler<Notification> notificationFileHandler = new FileHandler<>();

    
    /////////////////////////////////MAIN PAGE//////////////////////////////////
    public void MainPage(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_MainPage().setVisible(true);
    }

    public void ViewTasks(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_ViewTasks().setVisible(true);
    }

    public void TaskStatus(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_UpdateTasksStatus().setVisible(true);
    }

    public void RevenueDashboard(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_RevenueDashboard().setVisible(true);
    }

    public void TasksHistory(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_CheckTasksHistory().setVisible(true);
    }

    public void ReadCustomerReview(JFrame frame) {
        frame.dispose();
        new DeliveryRunner_ReadCustomerReview().setVisible(true);
    }

    public void LogOut(JFrame frame) {
        int option = JOptionPane.showOptionDialog(null, "Are you sure you want to log out?", 
            "Logout Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES");
        
        if (option == JOptionPane.YES_OPTION) {
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Goodbye, Delivery Runner!", "Logout Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // TASKS TABLE SEARCH FUNCTION
    public void TasksTableSearchField(JTable tasksTable, JTextField searchField) {
        String searchText = searchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) tasksTable.getModel());
        tasksTable.setRowSorter(sorter);

        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < tasksTable.getColumnCount(); i++) {
            tasksTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    setHorizontalAlignment(SwingConstants.LEFT);

                    if (!searchText.isEmpty() && value != null && value.toString().toLowerCase().contains(searchText.toLowerCase())) {
                        cell.setBackground(Color.YELLOW);
                    } else {
                        cell.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    }

                    return cell;
                }
            });
        }
        tasksTable.repaint();
    }
    //CHECK TASKS HISTORY SEACRCH FUNCTION
    public void TasksHistoryTableSearchField(JTable tasksHistoryTable, JTextField searchField) {
        String searchText = searchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) tasksHistoryTable.getModel());
        tasksHistoryTable.setRowSorter(sorter);

        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < tasksHistoryTable.getColumnCount(); i++) {
            tasksHistoryTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    setHorizontalAlignment(SwingConstants.LEFT);

                    if (!searchText.isEmpty() && value != null && value.toString().toLowerCase().contains(searchText.toLowerCase())) {
                        cell.setBackground(Color.YELLOW);
                    } else {
                        cell.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    }

                    return cell;
                }
            });
        }
        tasksHistoryTable.repaint();
    }
    //UPDATE TASKS STATUS SEACRCH FUNCTION
    public void updateTasksStatusTableSearchField(JTable updatetasksStatusTable, JTextField searchtxtfield) {
        String searchText = searchtxtfield.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) updatetasksStatusTable.getModel());
        updatetasksStatusTable.setRowSorter(sorter);

        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < updatetasksStatusTable.getColumnCount(); i++) {
            updatetasksStatusTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    setHorizontalAlignment(SwingConstants.LEFT);

                    if (!searchText.isEmpty() && value != null && value.toString().toLowerCase().contains(searchText.toLowerCase())) {
                        cell.setBackground(Color.YELLOW);
                    } else {
                        cell.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    }

                    return cell;
                }
            });
        }
        updatetasksStatusTable.repaint();
    }
    
public void TasksTableMouseReleased(JTable tasksTable, JTextField deliveryIDField, JTextField orderIDField, JTextField runnerIDField, JTextField addressField) {
    int selectedRow = tasksTable.getSelectedRow();

    if (selectedRow != -1) {
        String deliveryID = tasksTable.getValueAt(selectedRow, 0).toString();
        String orderID = tasksTable.getValueAt(selectedRow, 1).toString();
        String runnerID = tasksTable.getValueAt(selectedRow, 2).toString();
        String address = tasksTable.getValueAt(selectedRow, 3).toString();

        deliveryIDField.setText(deliveryID);
        orderIDField.setText(orderID);
        runnerIDField.setText(runnerID);
        addressField.setText(address);
    }
}
public void TasksPageClearData(JTable tasksTable, JTextField deliveryIDField, JTextField orderIDField, JTextField runnerIDField, JTextField housenumberField, JTextField streetNameField, JTextField residentialAreaField, JTextField postalCodeField, JTextField cityField, JTextField stateField, JTextField searchField) {
    tasksTable.clearSelection(); // Clear the selection in the table
    deliveryIDField.setText("");
    orderIDField.setText("");
    runnerIDField.setText("");
    housenumberField.setText("");
    streetNameField.setText("");
    residentialAreaField.setText("");
    postalCodeField.setText("");
    cityField.setText("");
    stateField.setText("");
    searchField.setText("");
    // Removed the line that resets the table
}

    public void TasksTableResetTable(JTable tasksTable) {
        DefaultTableModel model = (DefaultTableModel) tasksTable.getModel();
        model.setRowCount(0); // Clear all rows from the table
    }


public void TasksTableMouseReleased(JTable tasksTable, JTextField deliveryIDField, JTextField orderIDField, JTextField runnerIDField, JTextField housenumberField, JTextField streetNameField, JTextField residentialAreaField, JTextField postalCodeField, JTextField cityField, JTextField stateField) {
    int selectedRow = tasksTable.getSelectedRow();

    if (selectedRow != -1) {
        String deliveryID = tasksTable.getValueAt(selectedRow, 0).toString();
        String orderID = tasksTable.getValueAt(selectedRow, 1).toString();
        String runnerID = tasksTable.getValueAt(selectedRow, 2).toString();
        String houseNumber = tasksTable.getValueAt(selectedRow, 3).toString();
        String streetName = tasksTable.getValueAt(selectedRow, 4).toString();
        String residentialArea = tasksTable.getValueAt(selectedRow, 5).toString();
        String postalCode = tasksTable.getValueAt(selectedRow, 6).toString();
        String city = tasksTable.getValueAt(selectedRow, 7).toString();
        String state = tasksTable.getValueAt(selectedRow, 8).toString();

        deliveryIDField.setText(deliveryID);
        orderIDField.setText(orderID);
        runnerIDField.setText(runnerID);
        housenumberField.setText(houseNumber);
        streetNameField.setText(streetName);
        residentialAreaField.setText(residentialArea);
        postalCodeField.setText(postalCode);
        cityField.setText(city);
        stateField.setText(state);
    }
}
public void showAlertIfNoRowSelected(JTable tasksTable) {
    int selectedRow = tasksTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Please select any row from the table!", "Warning", JOptionPane.WARNING_MESSAGE);
    }
}

//  VIEW TASKS METHODS
    public void loadDeliveryTasksTable(DefaultTableModel model) {
        model.setRowCount(0); // Clear the table before loading

        try (BufferedReader br = new BufferedReader(new FileReader(deliveryFilePath))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Delivery object
                Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

                // Extract address components
                String houseNumber = delivery.deliveryAddress.houseNumber;
                String streetName = delivery.deliveryAddress.streetName;
                String residentialArea = delivery.deliveryAddress.residentialArea;
                String postalCode = delivery.deliveryAddress.postalCode;
                String city = delivery.deliveryAddress.city;
                String state = delivery.deliveryAddress.state;

                // Add the delivery data to the table model
                model.addRow(new Object[]{
                    delivery.deliveryID,
                    delivery.orderID,
                    delivery.deliveryRunnerID,
                    houseNumber,
                    streetName,
                    residentialArea,
                    postalCode,
                    city,
                    state
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // UPDATE TASKS STATUS METHODS
public void populateDeliveryTasksTableWithDetails(DefaultTableModel model) {
    model.setRowCount(0); // Clear the table before loading

    try (BufferedReader br = new BufferedReader(new FileReader(deliveryFilePath))) {
        String line;
        Gson gson = new Gson();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // Skip empty lines

            // Convert JSON line to Delivery object
            Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

            // Read the corresponding order from orders.txt
            Order order = getOrderById(delivery.orderID);

            if (order != null) {
                // Add the delivery data to the table model
                model.addRow(new Object[]{
                    order.orderID,          // Order ID
                    order.customerID,      // Customer ID
                    order.orderType,       // Order Type
                    order.deliveryFee,     // Delivery Fee
                    order.totalPrice,      // Total Price
                    order.orderStatus,    // Order Status (Ensure this is read correctly)
                    delivery.deliveryAddress.houseNumber,
                    delivery.deliveryAddress.streetName,
                    delivery.deliveryAddress.residentialArea,
                    delivery.deliveryAddress.postalCode,
                    delivery.deliveryAddress.city,
                    delivery.deliveryAddress.state
                });
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public Order getOrderById(String orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Order object
                Order order = gson.fromJson(line.trim(), Order.class);

                if (order.orderID.equals(orderID)) {
                    return order; // Return the order if the ID matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the order is not found
    }

public void loadRunnerReviewsTable(DefaultTableModel model) {
    model.setRowCount(0); // Clear the table before loading

    try (BufferedReader br = new BufferedReader(new FileReader(reviewFilePath))) {
        String line;
        Gson gson = new Gson();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // Skip empty lines
            
            Review review = gson.fromJson(line.trim(), Review.class);

            // Check if entityType is "D" (for Delivery Runner)
            if ("D".equals(review.entityType)) {
                model.addRow(new Object[]{
                    review.reviewID, 
                    review.customerID, 
                    review.entityType, 
                    review.reviewedEntityID, 
                    review.rating, 
                    review.comment
                });
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    // Method to calculate revenue by period
public Map<String, Double> getRevenueByPeriod(String runnerID, String period, LocalDate startDate, LocalDate endDate) {
    Map<String, Double> revenueMap = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order

    try (BufferedReader br = new BufferedReader(new FileReader(deliveryFilePath))) {
        String line;
        Gson gson = new Gson();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // Skip empty lines

            // Convert JSON line to Delivery object
            Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

            if (delivery.deliveryRunnerID.equals(runnerID)) {
                double deliveryFee = getDeliveryFee(delivery.orderID);
                String orderDateStr = getOrderDate(delivery.orderID);

                // Parse the order date
                LocalDate orderDate = LocalDate.parse(orderDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // Check if the order date is within the selected period (between startDate and endDate)
                if (!orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                    String periodKey = getPeriodKey(orderDateStr, period);
                    revenueMap.put(periodKey, revenueMap.getOrDefault(periodKey, 0.0) + deliveryFee);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return revenueMap;
}

    // Method to get delivery fee for an order
    private double getDeliveryFee(String orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Order object
                Order order = gson.fromJson(line.trim(), Order.class);

                if (order.orderID.equals(orderID)) {
                    return order.deliveryFee; // Return the delivery fee
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Return 0 if the order is not found
    }

    // Method to get order date
    private String getOrderDate(String orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Order object
                Order order = gson.fromJson(line.trim(), Order.class);

                if (order.orderID.equals(orderID)) {
                    return order.orderDate; // Return the order date
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // Return an empty string if the order is not found
    }

    // Method to get period key (daily, monthly, yearly)
    private String getPeriodKey(String orderDateStr, String period) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date orderDate = sdf.parse(orderDateStr);
            switch (period.toLowerCase()) {
                case "daily":
                    return new SimpleDateFormat("yyyy-MM-dd").format(orderDate);
                case "monthly":
                    return new SimpleDateFormat("yyyy-MM").format(orderDate);
                case "yearly":
                    return new SimpleDateFormat("yyyy").format(orderDate);
                default:
                    return "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public boolean updateOrderStatus(String orderID, String newStatus) {
        try {
            List<String> lines = new ArrayList<>();
            boolean orderFound = false;

            // Read the orders.txt file
            try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
                String line;
                Gson gson = new Gson();

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Skip empty lines

                    // Convert JSON line to Order object
                    Order order = gson.fromJson(line.trim(), Order.class);

                    if (order.orderID.equals(orderID)) {
                        // Update the order status
                        order.orderStatus = newStatus;
                        orderFound = true;
                    }

                    // Convert the updated order back to JSON and add it to the list
                    lines.add(gson.toJson(order));
                }
            }

            if (!orderFound) {
                JOptionPane.showMessageDialog(null, "Order not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Write the updated orders back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(orderFilePath))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // send notifications
    public void sendNotification(String userID, String notificationContent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("notifications.txt", true))) {
            // Generate the next sequential notification ID
            String notificationID = generateNextNotificationID();

            // Create a LinkedHashMap to maintain the order of fields
            Map<String, String> notificationMap = new LinkedHashMap<>();
            notificationMap.put("notificationID", notificationID);
            notificationMap.put("userID", userID);
            notificationMap.put("notificationContent", notificationContent);
            notificationMap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            // Convert the LinkedHashMap to JSON
            Gson gson = new Gson();
            String notificationJson = gson.toJson(notificationMap);

            // Write the notification to the file
            bw.write(notificationJson);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Add the getCustomerIDFromOrder Method
    public String getCustomerIDFromOrder(String orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Order object
                Order order = gson.fromJson(line.trim(), Order.class);

                if (order.orderID.equals(orderID)) {
                    return order.customerID; // Return the customer ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // Return an empty string if the order is not found
    }
    
    //Generate Sequential Notification IDs
    public String generateNextNotificationID() {
        int maxID = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("notifications.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Parse the notification ID from the JSON line
                JSONObject notification = new JSONObject(line.trim());
                String notificationID = notification.getString("notificationID");

                // Extract the numeric part of the ID
                int idNumber = Integer.parseInt(notificationID.substring(1)); // Remove the "N" prefix
                if (idNumber > maxID) {
                    maxID = idNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate the next ID
        return "N" + String.format("%04d", maxID + 1); // Format as N0001, N0002, etc.
    }
    //Format the Task Details 
    public String formatTaskDetails(String deliveryID, String orderID, String runnerID, String houseNumber, String streetName, String residentialArea, String postalCode, String city, String state) {
        return "Delivery ID: " + deliveryID + "\n" +
               "Order ID: " + orderID + "\n" +
               "Delivery Runner ID: " + runnerID + "\n" +
               "Delivery Address: " + houseNumber + ", " + streetName + ", " + residentialArea + ", " + postalCode + ", " + city + ", " + state;
    }
    
    // isRunnerAvailable Method
    public boolean isRunnerAvailable(String runnerID) {
        try (BufferedReader br = new BufferedReader(new FileReader("delivery.txt"))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to Delivery object
                Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

                // Check if the runner is assigned to any task
                if (runnerID.equals(delivery.deliveryRunnerID)) {
                    return false; // Runner is not available
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // Runner is available
    }

        // Method to Find the Next Available Runner
    public String findNextAvailableRunner() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            Gson gson = new Gson();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Convert JSON line to User object
                User user = gson.fromJson(line.trim(), User.class);

                // Check if the user is a delivery runner and not currently assigned to a task
                if ("DeliveryRunner".equals(user.role)) {
                    boolean isAvailable = isRunnerAvailable(user.userID);
                    if (isAvailable) {
                        return user.userID; // Return the available runner's ID
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // No available runner found
    }

   // Method to Reassign the Task
    public boolean reassignTask(String orderID, String newRunnerID) {
        try {
            // Read the delivery.txt file
            List<String> lines = new ArrayList<>();
            boolean taskFound = false;

            try (BufferedReader br = new BufferedReader(new FileReader("delivery.txt"))) {
                String line;
                Gson gson = new Gson();

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Skip empty lines

                    // Convert JSON line to Delivery object
                    Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

                    if (orderID.equals(delivery.orderID)) {
                        // Reassign the task to the new runner
                        delivery.deliveryRunnerID = newRunnerID;
                        taskFound = true;
                    }

                    // Convert the updated delivery back to JSON and add it to the list
                    lines.add(gson.toJson(delivery));
                }
            }

            if (!taskFound) {
                JOptionPane.showMessageDialog(null, "Task not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Write the updated deliveries back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("delivery.txt"))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

   // Method to Credit Back Delivery Fees
    public boolean creditDeliveryFee(String customerID, double deliveryFee) {
        try {
            // Read the accounts.txt file
            List<String> lines = new ArrayList<>();
            boolean customerFound = false;

            try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
                String line;
                Gson gson = new Gson();

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Skip empty lines

                    // Convert JSON line to Account object
                    Account account = gson.fromJson(line.trim(), Account.class);

                    if (customerID.equals(account.userID)) {
                        // Credit the delivery fee back to the customer's account
                        account.balance += deliveryFee;
                        customerFound = true;
                    }

                    // Convert the updated account back to JSON and add it to the list
                    lines.add(gson.toJson(account));
                }
            }

            if (!customerFound) {
                JOptionPane.showMessageDialog(null, "Customer account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Write the updated accounts back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("accounts.txt"))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update the Task Status
    public boolean updateTaskStatus(String orderID, String newStatus) {
        try {
            // Read the orders.txt file
            List<String> lines = new ArrayList<>();
            boolean orderFound = false;

            try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
                String line;
                Gson gson = new Gson();

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Skip empty lines

                    // Convert JSON line to Order object
                    Order order = gson.fromJson(line.trim(), Order.class);

                    if (order.orderID.equals(orderID)) {
                        // Update the order status
                        order.orderStatus = newStatus;
                        orderFound = true;
                    }

                    // Convert the updated order back to JSON and add it to the list
                    lines.add(gson.toJson(order));
                }
            }

            if (!orderFound) {
                JOptionPane.showMessageDialog(null, "Order not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Write the updated orders back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(orderFilePath))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // CHECK TASKS HISTORY
public void LoadTasksHistoryTable(JTable tasksHistoryTable) {
    DefaultTableModel model = (DefaultTableModel) tasksHistoryTable.getModel();
    model.setRowCount(0); // Clear the table before loading

    try (BufferedReader br = new BufferedReader(new FileReader(deliveryFilePath))) {
        String line;
        Gson gson = new Gson();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // Skip empty lines

            // Convert JSON line to Delivery object
            Delivery delivery = gson.fromJson(line.trim(), Delivery.class);

            // Read the corresponding order from orders.txt
            Order order = getOrderById(delivery.orderID);

            if (order != null) {
                // Add the delivery data to the table model
                model.addRow(new Object[]{
                    order.orderID,          
                    order.customerID,      
                    order.vendorID,         
                    order.orderStatus,    
                    delivery.deliveryID,  
                    delivery.deliveryAddress.houseNumber, 
                    delivery.deliveryAddress.streetName,
                    delivery.deliveryAddress.residentialArea, 
                    delivery.deliveryAddress.postalCode,   
                    delivery.deliveryAddress.city,        
                    delivery.deliveryAddress.state        
                });
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public boolean updateOrderType(String orderID, String newOrderType) {
        try {
            List<String> lines = new ArrayList<>();
            boolean orderFound = false;

            // Read the orders.txt file
            try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
                String line;
                Gson gson = new Gson();

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // Skip empty lines

                    // Convert JSON line to Order object
                    Order order = gson.fromJson(line.trim(), Order.class);

                    if (order.orderID.equals(orderID)) {
                        // Update the order type
                        order.orderType = newOrderType;
                        orderFound = true;
                    }

                    // Convert the updated order back to JSON and add it to the list
                    lines.add(gson.toJson(order));
                }
            }

            if (!orderFound) {
                JOptionPane.showMessageDialog(null, "Order not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Write the updated orders back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(orderFilePath))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
