/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import com.google.gson.reflect.TypeToken;
import com.mycompany.testing.Account;
import com.mycompany.testing.Address;
import com.mycompany.testing.FileHandler;
import com.mycompany.testing.Notification;
import com.mycompany.testing.Transaction;
import com.mycompany.testing.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jason01
 */

public class AdminFunctions {
    //FILE
    private final String userFilePath = "users.txt";
    private final String accountFilePath = "accounts.txt";
    private final String transactionFilePath = "transactions.txt";
    private final String notificationFilePath = "notifications.txt";
    private String username1;
    
    /////////////////////////////////MAIN PAGE//////////////////////////////////
    //GO MAIN PAGE    
    public void MainPage(JFrame frame, String Username) {
        frame.dispose();
        this.username1 = Username;
        new AdminMainPage(username1).setVisible(true);
    }
    
    //GO MANAGE USER PAGE
    public void UserManagement(JFrame frame, String Username) {
        frame.dispose();
        this.username1 = Username;
        new AdminManageUser(username1).setVisible(true);
    }
    
    //GO TOP UP PAGE
    public void TopUpCredit(JFrame frame, String Username) {
        frame.dispose();
        this.username1 = Username;
        new AdminTopUp(username1).setVisible(true);
    }
    
    //GO VIEW TRANSACTION PAGE
    public void GenerateReceipt(JFrame frame, String Username) {
        frame.dispose();
        this.username1 = Username;
        new AdminViewTransaction(username1).setVisible(true);
    }
    
    //LOG OUT
    public void LogOut(JFrame frame, String Username) {
        this.username1 = Username;
        int option = JOptionPane.showOptionDialog(null,"Are You Sure You Want To Logout?","Logout Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES");
        if (option == JOptionPane.YES_OPTION) {
            frame.dispose(); 
            JOptionPane.showMessageDialog(null, "Bye Bye Admin " + username1 + "!", "Logout Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Logout Cancel Successful.", "Cancel Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    ////////////////////////////////MANAGE USER/////////////////////////////////
    //UPDATE USER TABLE DATA
    public DefaultTableModel GetUserTableModel() {
        Type userType = new TypeToken<User>() {}.getType();
        FileHandler<User> fileHandler = new FileHandler<>();
        List<User> users = fileHandler.readTxt(userFilePath, userType);

        String[] columnNames = {"UserID", "Username", "Password", "Role", "Phone Number", "House Number", "Street Name", "Residential Area", "Postal Code", "City", "State"};

        Object[][] tableData = new Object[users.size()][columnNames.length];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            tableData[i][0] = user.getUserID();
            tableData[i][1] = user.getUsername();
            tableData[i][2] = user.getPassword();
            tableData[i][3] = user.getRole();
            tableData[i][4] = user.getPhoneNum();

            Address address = user.getAddress();
            tableData[i][5] = address.houseNumber;
            tableData[i][6] = address.streetName;
            tableData[i][7] = address.residentialArea;
            tableData[i][8] = address.postalCode;
            tableData[i][9] = address.city;
            tableData[i][10] = address.state;
        }

        return new DefaultTableModel(tableData, columnNames);
    }

    //REFRESH USER TABLE DATA
    public void LoadUserTable(JTable userTable) {
        DefaultTableModel tableModel = GetUserTableModel();

        userTable.setModel(tableModel);
    }
    
    //MAKE EACH WORD CAPITAL
    public String capitalizeEachWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(word.substring(0, 1).toUpperCase())
                          .append(word.substring(1).toLowerCase())
                          .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    //USER TABLE ADD USER
    public void UserTableAddUser(JTable userTable, DefaultTableModel model, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, JTextField searchField, JTextField phonenumberField, JTextField housenumberField, JTextField streetnameField, JTextField residentialareaField, JTextField postalcodeField, JTextField cityField, JTextField stateField, JCheckBox passwordCheckBox) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        String phonenumber = phonenumberField.getText();
        String housenumber = housenumberField.getText();
        String streetname = capitalizeEachWord(streetnameField.getText());
        String residentialarea = capitalizeEachWord(residentialareaField.getText());
        String postalcode = postalcodeField.getText();
        String city = capitalizeEachWord(cityField.getText());
        String state = capitalizeEachWord(stateField.getText());
         
        if (username.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Username.", "Username Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Password.", "Password Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(role.equals("-- CHOOSE --")){
            JOptionPane.showMessageDialog(null, "Please Select The Role.", "Role Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(phonenumber.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Phone Number.", "Phone Number Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(!phonenumber.matches("\\d+")){
            JOptionPane.showMessageDialog(null, "Phone Number Must Contain Only Numbers.", "Phone Number Invalid", JOptionPane.ERROR_MESSAGE);
            phonenumberField.setText("");
            return;
        } else if(housenumber.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please  Fill In The House Number.", "House Number Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(!housenumber.matches("\\d+")){
            JOptionPane.showMessageDialog(null, "House Number Must Contain Only Numbers.", "House Number Invalid", JOptionPane.ERROR_MESSAGE);
            housenumberField.setText("");
            return;
        } else if(streetname.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Street Name.", "Street Name Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(residentialarea.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Residential Area.", "Residential Are Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(postalcode.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The Postal Code.", "Postal Code Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(!postalcode.matches("\\d+")){
            JOptionPane.showMessageDialog(null, "Postal Code Must Contain Only Numbers.", "Postal Code Invalid", JOptionPane.ERROR_MESSAGE);
            postalcodeField.setText("");
            return;
        } else if(city.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The City.", "Ciyt Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(state.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill In The State.", "State Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        FileHandler<User> userFileHandler = new FileHandler<>();
        Type userType = new TypeToken<User>() {}.getType();

        List<User> users = userFileHandler.readTxt(userFilePath, userType);

        if (users == null) {
            users = new ArrayList<>();
        }

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) &&
                user.getPassword().equals(password) &&
                user.getRole().equalsIgnoreCase(role) &&
                user.getPhoneNum().equals(phonenumber) &&
                user.getAddress().houseNumber.equals(housenumber) &&
                user.getAddress().streetName.equalsIgnoreCase(streetname) &&
                user.getAddress().residentialArea.equalsIgnoreCase(residentialarea) &&
                user.getAddress().postalCode.equals(postalcode) &&
                user.getAddress().city.equalsIgnoreCase(city) &&
                user.getAddress().state.equalsIgnoreCase(state)) {
        
                usernameField.setText("");
                passwordField.setText("");
                phonenumberField.setText("");
                housenumberField.setText("");
                streetnameField.setText("");
                residentialareaField.setText("");
                postalcodeField.setText("");
                cityField.setText("");
                stateField.setText("");
                roleComboBox.setSelectedItem("-- CHOOSE --");
                searchField.setText("");
                passwordField.setEchoChar('*');
                passwordCheckBox.setSelected(false);
                userTable.clearSelection();
                JOptionPane.showMessageDialog(null, "User Already Exists. Please Enter Unique Details.", "Duplicate User", JOptionPane.ERROR_MESSAGE);
                roleComboBox.setEnabled(true);
                UserTableresetTable(userTable);
                return;
            }
        }
        
        String userID = UserTableGenerateUserID(users, role);

        Address address = new Address(housenumber, streetname, residentialarea, postalcode, city, state);
        
        int confirm = JOptionPane.showOptionDialog(null, "Are You Sure You Want To Add The Following User?\n\n" +"Username: " + username + "\n" +"Password: " + password + "\n" +"Role: " + role + "\n" +"Phone Number: " + phonenumber + "\n" +"Address: " + address.ToString() + "\n\n", "Confirm Add", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES");
        
        if (confirm == JOptionPane.YES_OPTION) {
            User newUser = new User(userID, username, password, role, phonenumber, address);

            users.add(newUser);

            userFileHandler.writeTxt(userFilePath, users);

            model.addRow(new Object[]{userID, username, password, role, phonenumber, housenumber, streetname, residentialarea, postalcode, city, state});
            
            if (role.equalsIgnoreCase("Customer")) {
                AddCustomerAccount(userID);
            }

            usernameField.setText("");
            passwordField.setText("");
            phonenumberField.setText("");
            housenumberField.setText("");
            streetnameField.setText("");
            residentialareaField.setText("");
            postalcodeField.setText("");
            cityField.setText("");
            stateField.setText("");
            roleComboBox.setSelectedItem("-- CHOOSE --");
            searchField.setText("");
            passwordField.setEchoChar('*');
            passwordCheckBox.setSelected(false);
            userTable.clearSelection();
            JOptionPane.showMessageDialog(null, "Record Add Successfully.", "Add Successful", JOptionPane.INFORMATION_MESSAGE);
            roleComboBox.setEnabled(true);
            UserTableresetTable(userTable);
        } else {
            usernameField.setText("");
            passwordField.setText("");
            phonenumberField.setText("");
            housenumberField.setText("");
            streetnameField.setText("");
            residentialareaField.setText("");
            postalcodeField.setText("");
            cityField.setText("");
            stateField.setText("");
            roleComboBox.setSelectedItem("-- CHOOSE --");
            searchField.setText("");
            passwordField.setEchoChar('*');
            passwordCheckBox.setSelected(false);
            userTable.clearSelection();
            JOptionPane.showMessageDialog(null, "Add User Canceled.", "Add Canceled", JOptionPane.INFORMATION_MESSAGE);
            roleComboBox.setEnabled(true);
            UserTableresetTable(userTable);
        }    
    }

    //USER TABLE SHOW PASSWORD
    public void UserTableShowPassword(JCheckBox passwordCheckBox, JPasswordField passwordField) {
        if (passwordCheckBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }
    
    //USER TABLE GENERATE USER ID
    private String UserTableGenerateUserID(List<User> users, String role) {
        String prefix = "";
        int maxNumber = 0;

        switch (role.toLowerCase()) {
            case "vendor":
                prefix = "V";
                break;
            case "customer":
                prefix = "C";
                break;
            case "deliveryrunner":
                prefix = "D";
                break;
            default:
                throw new IllegalArgumentException("Invalid Role: " + role);
        }

        for (User user : users) {
            if (user.getRole().equalsIgnoreCase(role) && user.getUserID().startsWith(prefix)) {
                String idNumber = user.getUserID().substring(1);
                int number = Integer.parseInt(idNumber);
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }

        return prefix + String.format("%03d", maxNumber + 1);
    }

    //CREATE CUSTORMER ACCOUNT
    public void AddCustomerAccount(String userID) {
        try {
            FileHandler<Account> accountFileHandler = new FileHandler<>();
            Type accountType = new TypeToken<Account>() {}.getType();

            List<Account> accounts = accountFileHandler.readTxt(accountFilePath, accountType);

            if (accounts == null) {
                accounts = new ArrayList<>();
            }

            String accountID = GenerateAccountID(accounts);

            Account newAccount = new Account(accountID, userID, 0.0);

            accounts.add(newAccount);

            accountFileHandler.writeTxt(accountFilePath, accounts);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //GENERATE ACCOUNT ID
    public String GenerateAccountID(List<Account> accounts) {
        if (accounts.isEmpty()) {
            return "A0001";
        }

        String lastAccountID = accounts.get(accounts.size() - 1).accountID;
        int numericPart = Integer.parseInt(lastAccountID.substring(1));
        return String.format("A%04d", numericPart + 1);
    }

    //USER TABLE UPDATE USER
    public void UserTableUpdateUser(JTable userTable, DefaultTableModel model, int selectedRow, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, JTextField searchField, JTextField phonenumberField, JTextField housenumberField, JTextField streetnameField, JTextField residentialareaField, JTextField postalcodeField, JTextField cityField, JTextField stateField, JCheckBox passwordCheckBox) {
        if (selectedRow == -1) {
            usernameField.setText("");
            passwordField.setText("");
            phonenumberField.setText("");
            housenumberField.setText("");
            streetnameField.setText("");
            residentialareaField.setText("");
            postalcodeField.setText("");
            cityField.setText("");
            stateField.setText("");
            roleComboBox.setSelectedItem("-- CHOOSE --");
            searchField.setText("");
            passwordField.setEchoChar('*');
            passwordCheckBox.setSelected(false);
            userTable.clearSelection();
            JOptionPane.showMessageDialog(null, "Please Select A Row To Update.", "Select Update Row", JOptionPane.WARNING_MESSAGE);
            UserTableresetTable(userTable);
        } else {
            selectedRow = userTable.convertRowIndexToModel(selectedRow);
            String userID = (String) model.getValueAt(selectedRow, 0);
            String oldUsername = (String) model.getValueAt(selectedRow, 1);
            String oldPassword = (String) model.getValueAt(selectedRow, 2);
            String oldRole = (String) model.getValueAt(selectedRow, 3);
            String oldPhonenumber = (String) model.getValueAt(selectedRow, 4);
            String oldHousenumber = (String) model.getValueAt(selectedRow, 5);
            String oldStreetname = (String) model.getValueAt(selectedRow, 6);
            String oldResidentialarea = (String) model.getValueAt(selectedRow, 7);
            String oldPostalcode = (String) model.getValueAt(selectedRow, 8);
            String oldCity = (String) model.getValueAt(selectedRow, 9);
            String oldState = (String) model.getValueAt(selectedRow, 10);

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String phonenumber = phonenumberField.getText();
            String housenumber = housenumberField.getText();
            String streetname = capitalizeEachWord(streetnameField.getText());
            String residentialarea = capitalizeEachWord(residentialareaField.getText());
            String postalcode = postalcodeField.getText();
            String city = capitalizeEachWord(cityField.getText());
            String state = capitalizeEachWord(stateField.getText());


            if (username.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Username.", "Username Invalid", JOptionPane.ERROR_MESSAGE);
                usernameField.setText(oldUsername);                               
            } else if(password.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Password.", "Password Invalid", JOptionPane.ERROR_MESSAGE);
                passwordField.setText(oldPassword);
            } else if(phonenumber.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Phone Number.", "Phone Number Invalid", JOptionPane.ERROR_MESSAGE);
                phonenumberField.setText(oldPhonenumber);
            } else if(!phonenumber.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Phone Number Must Contain Only Numbers.", "Password Invalid", JOptionPane.ERROR_MESSAGE);
                phonenumberField.setText(oldPhonenumber);
            } else if(housenumber.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid House Number.", "House Number Invalid", JOptionPane.ERROR_MESSAGE);
                housenumberField.setText(oldHousenumber);
            } else if(!housenumber.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "House Number Must Contain Only Numbers.", "House Number Invalid", JOptionPane.ERROR_MESSAGE);
                housenumberField.setText(oldHousenumber);
            } else if(streetname.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Street Name.", "Street Name Invalid", JOptionPane.ERROR_MESSAGE);
                streetnameField.setText(oldStreetname);
            } else if(residentialarea.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Residential Area.", "Residential Are Invalid", JOptionPane.ERROR_MESSAGE);
                residentialareaField.setText(oldResidentialarea);
            } else if(postalcode.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid Postal Code.", "Postal Code Invalid", JOptionPane.ERROR_MESSAGE);
                postalcodeField.setText(oldPostalcode);
            } else if(!postalcode.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "Postal Code Must Contain Only Numbers.", "Postal Code Invalid", JOptionPane.ERROR_MESSAGE);
                postalcodeField.setText(oldPostalcode);
            } else if(city.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid City.", "Ciyt Invalid", JOptionPane.ERROR_MESSAGE);
                cityField.setText(oldCity);
            } else if(state.isEmpty()){
                JOptionPane.showMessageDialog(null, "Invalid State.", "State Invalid", JOptionPane.ERROR_MESSAGE);
                stateField.setText(oldState);
            }else {
                StringBuilder confirmationMessage = new StringBuilder("Are You Sure You Want To Update The User Details?\n\n");

                boolean changesMade = false;

                if (!oldUsername.equals(username)) {
                    confirmationMessage.append("Username: ").append(oldUsername).append(" --> ").append(username).append("\n");
                    changesMade = true;
                }
                if (!oldPassword.equals(password)) {
                    confirmationMessage.append("Password: ").append(oldPassword).append(" --> ").append(password).append("\n");
                    changesMade = true;
                }
                if (!oldPhonenumber.equals(phonenumber)) {
                    confirmationMessage.append("Phone Number: ").append(oldPhonenumber).append(" --> ").append(phonenumber).append("\n");
                    changesMade = true;
                }
                if (!oldHousenumber.equals(housenumber)) {
                    confirmationMessage.append("House Number: ").append(oldHousenumber).append(" --> ").append(housenumber).append("\n");
                    changesMade = true;
                }
                if (!oldStreetname.equals(streetname)) {
                    confirmationMessage.append("Street Name: ").append(oldStreetname).append(" --> ").append(streetname).append("\n");
                    changesMade = true;
                }
                if (!oldResidentialarea.equals(residentialarea)) {
                    confirmationMessage.append("Residential Area: ").append(oldResidentialarea).append(" --> ").append(residentialarea).append("\n");
                    changesMade = true;
                }
                if (!oldPostalcode.equals(postalcode)) {
                    confirmationMessage.append("Postal Code: ").append(oldPostalcode).append(" --> ").append(postalcode).append("\n");
                    changesMade = true;
                }
                if (!oldCity.equals(city)) {
                    confirmationMessage.append("City: ").append(oldCity).append(" --> ").append(city).append("\n");
                    changesMade = true;
                }
                if (!oldState.equals(state)) {
                    confirmationMessage.append("State: ").append(oldState).append(" --> ").append(state).append("\n");
                    changesMade = true;
                }
                
                if (!changesMade) {
                    usernameField.setText("");
                    passwordField.setText("");
                    phonenumberField.setText("");
                    housenumberField.setText("");
                    streetnameField.setText("");
                    residentialareaField.setText("");
                    postalcodeField.setText("");
                    cityField.setText("");
                    stateField.setText("");
                    roleComboBox.setSelectedItem("-- CHOOSE --");
                    searchField.setText("");
                    passwordField.setEchoChar('*');
                    passwordCheckBox.setSelected(false);
                    userTable.clearSelection();
                    JOptionPane.showMessageDialog(null, "No Changes Were Made.", "Update Successful", JOptionPane.WARNING_MESSAGE);
                    roleComboBox.setEnabled(true);
                    UserTableresetTable(userTable);
                    return;
                }

                int confirmation = JOptionPane.showOptionDialog(null, confirmationMessage.toString() + "\n", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES");
                
                if (confirmation == JOptionPane.YES_OPTION) {
                    FileHandler<User> fileHandler = new FileHandler<>();
                    Type userType = new TypeToken<User>() {}.getType();
                    List<User> users = fileHandler.readTxt(userFilePath, userType);

                    if (users != null) {
                        for (User user : users) {
                            if (user.getUserID().equals(users.get(selectedRow).getUserID())) {
                                continue;
                            }

                            if (user.getUsername().equalsIgnoreCase(username) &&
                                user.getPassword().equals(password) &&
                                user.getPhoneNum().equals(phonenumber) &&
                                user.getAddress().houseNumber.equals(housenumber) &&
                                user.getAddress().streetName.equalsIgnoreCase(streetname) &&
                                user.getAddress().residentialArea.equalsIgnoreCase(residentialarea) &&
                                user.getAddress().postalCode.equals(postalcode) &&
                                user.getAddress().city.equalsIgnoreCase(city) &&
                                user.getAddress().state.equalsIgnoreCase(state)) {
                                
                                usernameField.setText("");
                                passwordField.setText("");
                                phonenumberField.setText("");
                                housenumberField.setText("");
                                streetnameField.setText("");
                                residentialareaField.setText("");
                                postalcodeField.setText("");
                                cityField.setText("");
                                stateField.setText("");
                                roleComboBox.setSelectedItem("-- CHOOSE --");
                                searchField.setText("");
                                passwordField.setEchoChar('*');
                                passwordCheckBox.setSelected(false);
                                userTable.clearSelection();
                                JOptionPane.showMessageDialog(null, "User Already Exists. Please Enter Unique Details.", "Duplicate User", JOptionPane.ERROR_MESSAGE);
                                roleComboBox.setEnabled(true);
                                UserTableresetTable(userTable);
                                return;
                            }
                        }

                        model.setValueAt(userID, selectedRow, 0);
                        model.setValueAt(username, selectedRow, 1);
                        model.setValueAt(password, selectedRow, 2);
                        model.setValueAt(oldRole, selectedRow, 3);
                        model.setValueAt(phonenumber, selectedRow, 4);
                        model.setValueAt(housenumber, selectedRow, 5);
                        model.setValueAt(streetname, selectedRow, 6);
                        model.setValueAt(residentialarea, selectedRow, 7);
                        model.setValueAt(postalcode, selectedRow, 8);
                        model.setValueAt(city, selectedRow, 9);
                        model.setValueAt(state, selectedRow, 10);

                        User user = users.get(selectedRow);  

                        // Update values using setters
                        user.setUserID(userID);  
                        user.setUsername(username);  
                        user.setPassword(password);  
                        user.setRole(oldRole);  
                        user.setPhoneNum(phonenumber);  

                        Address addr = user.getAddress();  
                        addr.setHouseNumber(housenumber);  
                        addr.setStreetName(streetname);  
                        addr.setResidentialArea(residentialarea);  
                        addr.setPostalCode(postalcode);  
                        addr.setCity(city);  
                        addr.setState(state);  


                        fileHandler.writeTxt(userFilePath, users);

                        usernameField.setText("");
                        passwordField.setText("");
                        phonenumberField.setText("");
                        housenumberField.setText("");
                        streetnameField.setText("");
                        residentialareaField.setText("");
                        postalcodeField.setText("");
                        cityField.setText("");
                        stateField.setText("");
                        roleComboBox.setSelectedItem("-- CHOOSE --");
                        searchField.setText("");
                        passwordField.setEchoChar('*');
                        passwordCheckBox.setSelected(false);
                        userTable.clearSelection();                        
                        JOptionPane.showMessageDialog(null, "User Updated Successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                        roleComboBox.setEnabled(true);
                        UserTableresetTable(userTable);
                    }
                }  else {
                    usernameField.setText("");
                    passwordField.setText("");
                    phonenumberField.setText("");
                    housenumberField.setText("");
                    streetnameField.setText("");
                    residentialareaField.setText("");
                    postalcodeField.setText("");
                    cityField.setText("");
                    stateField.setText("");
                    roleComboBox.setSelectedItem("-- CHOOSE --");
                    searchField.setText("");
                    passwordField.setEchoChar('*');
                    passwordCheckBox.setSelected(false);
                    userTable.clearSelection();
                    JOptionPane.showMessageDialog(null, "Update Canceled.", "Update Canceled", JOptionPane.INFORMATION_MESSAGE);
                    roleComboBox.setEnabled(true);
                    UserTableresetTable(userTable);
                }
            }     
        }
    }

    //MANAGE USER PAGE CLEAR DATA
    public void ManageUserPageClearData(JTable userTable, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, JTextField searchField, JTextField phonenumberField, JTextField housenumberField, JTextField streetnameField, JTextField residentialareaField, JTextField postalcodeField, JTextField cityField, JTextField stateField, JCheckBox passwordCheckBox) {
        usernameField.setText("");
        passwordField.setText("");
        phonenumberField.setText("");
        housenumberField.setText("");
        streetnameField.setText("");
        residentialareaField.setText("");
        postalcodeField.setText("");
        cityField.setText("");
        stateField.setText("");
        roleComboBox.setSelectedItem("-- CHOOSE --");
        searchField.setText("");
        passwordField.setEchoChar('*');
        passwordCheckBox.setSelected(false);
        userTable.clearSelection();
        roleComboBox.setEnabled(true);
        
        UserTableresetTable(userTable);
    }

    //USER TABLE RESET TABLE 
    public void UserTableresetTable(JTable userTable) {
        TableRowSorter<?> sorter = (TableRowSorter<?>) userTable.getRowSorter();
        if (sorter != null) {
            sorter.setRowFilter(null);
        }

        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setCellRenderer(null);
        }

        userTable.repaint();
    }

    //USER TABLE DELETA USER
    public void UserTableDeleteUser(JTable userTable, DefaultTableModel model, int selectedRow, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, JTextField searchField, JTextField phonenumberField, JTextField housenumberField, JTextField streetnameField, JTextField residentialareaField, JTextField postalcodeField, JTextField cityField, JTextField stateField, JCheckBox passwordCheckBox) {
        if (selectedRow == -1) {
            usernameField.setText("");
            passwordField.setText("");
            phonenumberField.setText("");
            housenumberField.setText("");
            streetnameField.setText("");
            residentialareaField.setText("");
            postalcodeField.setText("");
            cityField.setText("");
            stateField.setText("");
            roleComboBox.setSelectedItem("-- CHOOSE --");
            searchField.setText("");
            passwordField.setEchoChar('*');
            passwordCheckBox.setSelected(false);
            userTable.clearSelection();
            JOptionPane.showMessageDialog(null, "Please Select A Row To Delete.", "Select Delete Row", JOptionPane.WARNING_MESSAGE);
            roleComboBox.setEnabled(true);
            UserTableresetTable(userTable);
        } else {
            selectedRow = userTable.convertRowIndexToModel(selectedRow);
            String userID = (String) model.getValueAt(selectedRow, 0); 
            String Username = (String) model.getValueAt(selectedRow, 1);
            String Password = (String) model.getValueAt(selectedRow, 2); 
            String Role = (String) model.getValueAt(selectedRow, 3);
            String Phonenumber = (String) model.getValueAt(selectedRow, 4);
            String Housenumber = (String) model.getValueAt(selectedRow, 5);
            String Streetname = (String) model.getValueAt(selectedRow, 6);
            String Residentialarea = (String) model.getValueAt(selectedRow, 7);
            String Postalcode = (String) model.getValueAt(selectedRow, 8);
            String City = (String) model.getValueAt(selectedRow, 9);
            String State = (String) model.getValueAt(selectedRow, 10);

            Address address = new Address(Housenumber, Streetname, Residentialarea, Postalcode, City, State);
            
            Type accountType = new TypeToken<Account>() {}.getType();
            FileHandler<Account> accountFileHandler = new FileHandler<>();
            List<Account> accounts = accountFileHandler.readTxt(accountFilePath, accountType);
            
            String accountbalance = "0";
            for (Account account : accounts) {
                if (account.userID.equals(userID)) {
                    accountbalance = String.valueOf(account.balance);
                    break;
                }
            }
            
            String message = "Are You Sure You Want To Delete The User: \n\n" + "Username: " + Username + "\n" + "Password: " + Password + "\n" + "Role: " + Role + "\n" + "Phone Number: " + Phonenumber + "\n" + "Address: " + address.ToString();

            if (Role.equals("Customer")) {
                message += "\nAccount Balance: " + accountbalance;
                
                double balance = Double.parseDouble(accountbalance);
                if (balance > 0) {
                    int option = JOptionPane.showOptionDialog(null, "The Customer Account Remaining Balance " + accountbalance + " Will Also Be Deleted.", "Reminder", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"CONTINUE", "CANCEL"}, "CONTINUE");
                    if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
                        usernameField.setText("");
                        passwordField.setText("");
                        phonenumberField.setText("");
                        housenumberField.setText("");
                        streetnameField.setText("");
                        residentialareaField.setText("");
                        postalcodeField.setText("");
                        cityField.setText("");
                        stateField.setText("");
                        roleComboBox.setSelectedItem("-- CHOOSE --");
                        searchField.setText("");
                        passwordField.setEchoChar('*');
                        passwordCheckBox.setSelected(false);
                        userTable.clearSelection();
                        JOptionPane.showMessageDialog(null, "Deletion Canceled.", "Delete Canceled", JOptionPane.INFORMATION_MESSAGE);
                        roleComboBox.setEnabled(true);
                        UserTableresetTable(userTable);
                        return;
                    }
                }
            }

            int confirm = JOptionPane.showOptionDialog(null, message + "\n\n", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES"); 
            
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);

                FileHandler<User> fileHandler = new FileHandler<>();
                Type userType = new TypeToken<User>() {}.getType();
                List<User> users = fileHandler.readTxt(userFilePath, userType);

                if (users != null && selectedRow >= 0) {
                    users.remove(selectedRow);

                    fileHandler.writeTxt(userFilePath, users);
                }

                if (accounts != null && selectedRow >= 0) {
                    Account accountToRemove = null;

                    for (Account account : accounts) {
                        if (account.userID.equals(userID)) {
                            accountToRemove = account;
                            break;
                        }
                    }

                    if (accountToRemove != null) {
                        accounts.remove(accountToRemove);
                    }

                    accountFileHandler.writeTxt(accountFilePath, accounts);
                }
                
                usernameField.setText("");
                passwordField.setText("");
                phonenumberField.setText("");
                housenumberField.setText("");
                streetnameField.setText("");
                residentialareaField.setText("");
                postalcodeField.setText("");
                cityField.setText("");
                stateField.setText("");
                roleComboBox.setSelectedItem("-- CHOOSE --");
                searchField.setText("");
                passwordField.setEchoChar('*');
                passwordCheckBox.setSelected(false);
                userTable.clearSelection();
                JOptionPane.showMessageDialog(null, "User Deleted Successfully.", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);
                roleComboBox.setEnabled(true);
                UserTableresetTable(userTable);
            } else {
                usernameField.setText("");
                passwordField.setText("");
                phonenumberField.setText("");
                housenumberField.setText("");
                streetnameField.setText("");
                residentialareaField.setText("");
                postalcodeField.setText("");
                cityField.setText("");
                stateField.setText("");
                roleComboBox.setSelectedItem("-- CHOOSE --");
                searchField.setText("");
                passwordField.setEchoChar('*');
                passwordCheckBox.setSelected(false);
                userTable.clearSelection();
                JOptionPane.showMessageDialog(null, "Deletion Canceled.", "Delete Canceled", JOptionPane.INFORMATION_MESSAGE);
                roleComboBox.setEnabled(true);
                UserTableresetTable(userTable);
            }
        }
    }    

    //USER TABLE MOUSE RELEASED
    public void UserTableMouseReleased(JTable userTable, JTextField usernameField, JPasswordField passwordField, JComboBox<String> roleComboBox, JTextField phonenumberField, JTextField housenumberField, JTextField streetnameField, JTextField residentialareaField, JTextField postalcodeField, JTextField cityField, JTextField stateField){
        int selectedRow = userTable.getSelectedRow();
        
        if (selectedRow != -1) {
            String username = userTable.getValueAt(selectedRow, 1).toString();
            String password = userTable.getValueAt(selectedRow, 2).toString();
            String role = userTable.getValueAt(selectedRow, 3).toString();
            String phonenumber = userTable.getValueAt(selectedRow, 4).toString();
            String housenumber = userTable.getValueAt(selectedRow, 5).toString();
            String streetname = userTable.getValueAt(selectedRow, 6).toString();
            String residentialarea = userTable.getValueAt(selectedRow, 7).toString();
            String postalcode = userTable.getValueAt(selectedRow, 8).toString();
            String city = userTable.getValueAt(selectedRow, 9).toString();
            String state = userTable.getValueAt(selectedRow, 10).toString();
            

            usernameField.setText(username);
            passwordField.setText(password);
            roleComboBox.setSelectedItem(role);
            roleComboBox.setEnabled(false);
            phonenumberField.setText(phonenumber);
            housenumberField.setText(housenumber);
            streetnameField.setText(streetname);
            residentialareaField.setText(residentialarea);
            postalcodeField.setText(postalcode);
            cityField.setText(city);
            stateField.setText(state);
        }
    }

    // USER TABLE ROLE DISABLE SELECTION
    public void handleRoleSelection(JTable userTable, JComboBox<String> roleComboBox) {
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow != -1) {
            JOptionPane.showMessageDialog(null, "You Are Not Allowed To Change The Role.", "Access Denied", JOptionPane.ERROR_MESSAGE);
        } else {
            roleComboBox.setEnabled(true);
        }
    }

    //USER TABLE SEARCH FIELD
    public void UserTableSearchField(JTable userTable, JTextField searchField) {
        String searchText = searchField.getText().trim();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) userTable.getModel());
        userTable.setRowSorter(sorter);

        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
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
        userTable.repaint();
    }

    //////////////////////////////////TOP UP////////////////////////////////////
    //UPDATE ACCOUNT TABLE DATA
    public DefaultTableModel GetAccountTableModel() {
        Type userType = new TypeToken<User>() {}.getType();
        FileHandler<User> userFileHandler = new FileHandler<>();
        List<User> users = userFileHandler.readTxt(userFilePath, userType);

        Type accountType = new TypeToken<Account>() {}.getType();
        FileHandler<Account> accountFileHandler = new FileHandler<>();
        List<Account> accounts = accountFileHandler.readTxt(accountFilePath, accountType);

        String[] columnNames = {"AccountID", "UserID", "Username", "Balance"};

        Object[][] tableData = new Object[accounts.size()][4];
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            tableData[i][0] = account.accountID;
            tableData[i][1] = account.userID;

            String username = "Unknown";
            for (User user : users) {
                if (user.getUserID().equals(account.userID)) {
                    username = user.getUsername();
                    break;
                }
            }

            tableData[i][2] = username;
            tableData[i][3] = account.balance;
        }

        return new DefaultTableModel(tableData, columnNames);
    }

    //REFRESH ACCOUNT TABLE DATA
    public void LoadAccountTable(JTable accounttable) {
        DefaultTableModel tableModel = GetAccountTableModel();

        accounttable.setModel(tableModel);
    }

    // ACCOUNT TABLE MOUSE RELEASED
    public void AccountTableMouseReleased(JTable accountTable, JTextField accountidField, JTextField useridField, JTextField usernameField, JTextField balanceField, JTextField valueField) {
        int selectedRow = accountTable.getSelectedRow();

        if (selectedRow != -1) {
            String accountID = accountTable.getValueAt(selectedRow, 0).toString();
            String userID = accountTable.getValueAt(selectedRow, 1).toString();
            String username = accountTable.getValueAt(selectedRow, 2).toString();
            String balance = accountTable.getValueAt(selectedRow, 3).toString();

            accountidField.setText(accountID);
            useridField.setText(userID);
            usernameField.setText(username);
            balanceField.setText(balance);
            
            valueField.setEditable(true);
        }
    }

    //TOP UP PAGE ERROR DIALOG
    public void TopUpPageShowErrorDialog(JTextField field, String fieldName, JTable accountTable) {
        String fieldValue = field.getText();
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Select A Customer From The Table First.", "Error", JOptionPane.ERROR_MESSAGE);
            field.setEditable(false);
        } else {
            JOptionPane.showMessageDialog(null, fieldName + " Field Cannot Be Edited.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //ACCOUNT TABLE SEARCH FIELD
    public void AccountTableSearchField(JTable accountTable, JTextField searchField){
        String searchText = searchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) accountTable.getModel());
        accountTable.setRowSorter(sorter); 
        
        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < accountTable.getColumnCount(); i++) {
            accountTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
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
        accountTable.repaint();
    }

    //TOP UP PAGE CLEAR DATA
    public void TopUpPageClearData(JTable accountTable, JTextField accountidField, JTextField useridField, JTextField usernameField, JTextField balanceField, JTextField valueField, JTextField searchField){
        accountTable.clearSelection();
        accountidField.setText("");
        useridField.setText("");
        usernameField.setText("");
        balanceField.setText("");
        valueField.setText("");
        searchField.setText("");
        AccountTableresetTable(accountTable);
    }

    //ACCOUNT TABLE RESET TABLE 
    public void AccountTableresetTable(JTable accountTable) {
        TableRowSorter<?> sorter = (TableRowSorter<?>) accountTable.getRowSorter();
        if (sorter != null) {
            sorter.setRowFilter(null);
        }

        for (int i = 0; i < accountTable.getColumnCount(); i++) {
            accountTable.getColumnModel().getColumn(i).setCellRenderer(null);
        }

        accountTable.repaint();
    }
    
    // ACCOUNT TABLE ADD BALANCE
    public void AccountTableAddBalance(String username12, JFrame topup, JTable accountTable, DefaultTableModel model, int selectedRow, JTextField accountidField, JTextField useridField, JTextField usernameField, JTextField balanceField, JTextField valueField, JTextField searchField) {
        this.username1 = username12;
        try {
            if (selectedRow == -1) {
                accountTable.clearSelection();
                accountidField.setText("");
                useridField.setText("");
                usernameField.setText("");
                balanceField.setText("");
                valueField.setText("");
                searchField.setText("");
                JOptionPane.showMessageDialog(null, "Please Select A Customer From The Table First.", "Error", JOptionPane.ERROR_MESSAGE);
                AccountTableresetTable(accountTable);
                return;
            }

            int modelRow = accountTable.convertRowIndexToModel(selectedRow);

            if (valueField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter A Value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valueToAdd = Double.parseDouble(valueField.getText());

            if (valueToAdd <= 0) {
                JOptionPane.showMessageDialog(null, "The value must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                valueField.setText("");
                return;
            }

            double currentBalance = Double.parseDouble(model.getValueAt(modelRow, 3).toString());
            double newBalance = currentBalance + valueToAdd;
            String customerID = model.getValueAt(modelRow, 1).toString();
            String username = model.getValueAt(modelRow, 2).toString();

            int confirmation = JOptionPane.showOptionDialog(null,"You Are About To Add " + valueToAdd + " To The Selected Customer's Account.\n\n" + "User ID: " + useridField.getText() + "\n" + "Username: " + usernameField.getText() + "\n" + "Current Balance: " + currentBalance + "\n" + "New Balance: " + newBalance + "\n\n" + "Do You Want To Proceed?" + "\n\n", "Confirm Account Update", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "YES");

            if (confirmation != JOptionPane.YES_OPTION) {
                accountTable.clearSelection();
                accountidField.setText("");
                useridField.setText("");
                usernameField.setText("");
                balanceField.setText("");
                valueField.setText("");
                searchField.setText("");
                JOptionPane.showMessageDialog(null, "Add Canceled.", "Add Canceled", JOptionPane.INFORMATION_MESSAGE);
                AccountTableresetTable(accountTable);
                return;
            }

            model.setValueAt(newBalance, modelRow, 3);

            if (!searchField.getText().trim().isEmpty()) {
                AccountTableresetTable(accountTable);
            }
            
            accountTable.clearSelection();
            accountidField.setText("");
            useridField.setText("");
            usernameField.setText("");
            balanceField.setText("");
            valueField.setText("");
            searchField.setText("");
            int option = JOptionPane.showOptionDialog(null, "Balance Updated Successfully.", "Update Successfully", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK", "RECEIPT"}, "OK");
            
            FileHandler<Transaction> transactionFileHandler = new FileHandler<>();
            Type transactionType = new TypeToken<Transaction>() {}.getType();
            List<Transaction> transactions = transactionFileHandler.readTxt(transactionFilePath, transactionType);
            
            String transactionID = GenerateTransactionID(transactions);
            
            if (transactions == null) {
                transactions = new ArrayList<>();
            }

            Transaction newTransaction = new Transaction(transactionID, customerID, valueToAdd, newBalance);

            String transactionDateTime = newTransaction.getDate();

            String[] dateTimeParts = transactionDateTime.split(" ");
            String transactionDate = dateTimeParts[0];
            String transactionTime = dateTimeParts[1];

            String accountBalance = String.valueOf(newBalance);
            String valueadd = String.valueOf(valueToAdd);
            
            transactions.add(newTransaction);

            transactionFileHandler.writeTxt(transactionFilePath, transactions);
            
            if (option == JOptionPane.YES_OPTION) {
                
            } else if (option == JOptionPane.NO_OPTION) {
                AdminReceipt adminReceipt = new AdminReceipt(this, false, username1);
                adminReceipt.Receipt(username1, transactionID, customerID, username, valueadd, transactionDate, transactionTime, accountBalance);
                topup.dispose();
                adminReceipt.setVisible(true);
            }
            
            FileHandler<Account> accountFileHandler = new FileHandler<>();
            Type accountType = new TypeToken<Account>() {}.getType();
            List<Account> accounts = accountFileHandler.readTxt(accountFilePath, accountType);

            String selectedAccountID = model.getValueAt(modelRow, 0).toString();

            for (Account account : accounts) {
                if (account.accountID.equals(selectedAccountID)) {
                    account.balance = newBalance;
                    break;
                }
            }

            FileHandler<Notification> notificationFileHandler = new FileHandler<>();
            Type notificationType = new TypeToken<Notification>() {}.getType();
            List<Notification> notifications = notificationFileHandler.readTxt(notificationFilePath, notificationType);
            
            String notificationID = GenerateNotificationID(notifications);
            
            if (notifications == null) {
                notifications = new ArrayList<>();
            }
            
            String notificationContent = "A deposit of " + String.format("%.2f", valueToAdd) + " was made to your account. New balance: " + String.format("%.2f", newBalance) + ".";

            Notification newNotification = new Notification(notificationID, customerID, notificationContent);
            
            notifications.add(newNotification);

            notificationFileHandler.writeTxt(notificationFilePath, notifications);
            accountFileHandler.writeTxt(accountFilePath, accounts);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Value. Please Enter A Numeric Value.", "Error", JOptionPane.ERROR_MESSAGE);
            valueField.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Unexpected Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ACCOUNT TABLE DEDUCT BALANCE
    public void AccountTableDeductBalance(String username12, JFrame deduct, JTable accountTable, DefaultTableModel model, int selectedRow, JTextField accountidField, JTextField useridField, JTextField usernameField, JTextField balanceField, JTextField valueField, JTextField searchField) {
        this.username1 = username12;
        try {
            if (selectedRow == -1) {
                accountTable.clearSelection();
                accountidField.setText("");
                useridField.setText("");
                usernameField.setText("");
                balanceField.setText("");
                valueField.setText("");
                searchField.setText("");
                JOptionPane.showMessageDialog(null, "Please Select A Customer From The Table First.", "Error", JOptionPane.ERROR_MESSAGE);
                AccountTableresetTable(accountTable);
                return;
            }

            int modelRow = accountTable.convertRowIndexToModel(selectedRow);
            
            if (valueField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter A Value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double valueToDeduct = Double.parseDouble(valueField.getText());

            if (valueToDeduct <= 0) {
                JOptionPane.showMessageDialog(null, "The value must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                valueField.setText("");
                return;
            }

            double currentBalance = Double.parseDouble(model.getValueAt(modelRow, 3).toString());
            double newBalance = currentBalance - valueToDeduct;
            String customerID = model.getValueAt(modelRow, 1).toString();
            String username = model.getValueAt(modelRow, 2).toString();

            int confirmation = JOptionPane.showOptionDialog(null,"You Are About To Deduct " + valueToDeduct + " To The Selected Customer's Account.\n\n" + "User ID: " + useridField.getText() + "\n" + "Username: " + usernameField.getText() + "\n" + "Current Balance: " + currentBalance + "\n" + "New Balance: " + newBalance + "\n\n" + "Do You Want To Proceed?" + "\n\n", "Confirm Account Update", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"YES", "NO"}, "NO");

            if (confirmation != JOptionPane.YES_OPTION) {
                accountTable.clearSelection();
                accountidField.setText("");
                useridField.setText("");
                usernameField.setText("");
                balanceField.setText("");
                valueField.setText("");
                searchField.setText("");
                JOptionPane.showMessageDialog(null, "Deduct Canceled.", "Deduct Canceled", JOptionPane.INFORMATION_MESSAGE);
                AccountTableresetTable(accountTable);
                return;
            }

            model.setValueAt(newBalance, modelRow, 3);

            if (!searchField.getText().trim().isEmpty()) {
                AccountTableresetTable(accountTable);
            }
            
            accountTable.clearSelection();
            accountidField.setText("");
            useridField.setText("");
            usernameField.setText("");
            balanceField.setText("");
            valueField.setText("");
            searchField.setText("");
            int option = JOptionPane.showOptionDialog(null, "Balance Updated Successfully.", "Update Successfully", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK", "RECEIPT"}, "OK");

            FileHandler<Transaction> transactionFileHandler = new FileHandler<>();
            Type transactionType = new TypeToken<Transaction>() {}.getType();
            List<Transaction> transactions = transactionFileHandler.readTxt(transactionFilePath, transactionType);
            
            String transactionID = GenerateTransactionID(transactions);
            
            if (transactions == null) {
                transactions = new ArrayList<>();
            }

            Transaction newTransaction = new Transaction(transactionID, customerID, -valueToDeduct, newBalance);

            String transactionDateTime = newTransaction.getDate();

            String[] dateTimeParts = transactionDateTime.split(" ");
            String transactionDate = dateTimeParts[0];
            String transactionTime = dateTimeParts[1];

            String accountBalance = String.valueOf(newBalance);
            String valuededuct = String.valueOf(-valueToDeduct);
            
            transactions.add(newTransaction);

            transactionFileHandler.writeTxt(transactionFilePath, transactions);
            
            if (option == JOptionPane.YES_OPTION) {
                
            } else if (option == JOptionPane.NO_OPTION) {
                AdminReceipt adminReceipt = new AdminReceipt(this, false, username1);
                adminReceipt.Receipt(username1, transactionID, customerID, username, valuededuct, transactionDate, transactionTime, accountBalance); // Call Receipt on the instance
                deduct.dispose();
                adminReceipt.setVisible(true);
            }
            
            FileHandler<Account> accountFileHandler = new FileHandler<>();
            Type accountType = new TypeToken<Account>() {}.getType();
            List<Account> accounts = accountFileHandler.readTxt(accountFilePath, accountType);

            String selectedAccountID = accountTable.getValueAt(modelRow, 0).toString();

            for (Account account : accounts) {
                if (account.accountID.equals(selectedAccountID)) {
                    account.balance = newBalance;
                    break;
                }
            }

            accountFileHandler.writeTxt(accountFilePath, accounts);
            
            FileHandler<Notification> notificationFileHandler = new FileHandler<>();
            Type notificationType = new TypeToken<Notification>() {}.getType();
            List<Notification> notifications = notificationFileHandler.readTxt(notificationFilePath, notificationType);
            
            String notificationID = GenerateNotificationID(notifications);
            
            if (notifications == null) {
                notifications = new ArrayList<>();
            }
            
            String notificationContent = "A deduction of " + String.format("%.2f", valueToDeduct) + " was made from your account. Remaining balance: " + String.format("%.2f", newBalance) + ".";

            Notification newNotification = new Notification(notificationID, customerID, notificationContent);
            
            notifications.add(newNotification);

            notificationFileHandler.writeTxt(notificationFilePath, notifications);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Value. Please Enter A Numeric Value.", "Error", JOptionPane.ERROR_MESSAGE);
            valueField.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Unexpected Error Occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //GENERATE TRANSACTION ID
    public String GenerateTransactionID(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return "T0001";
        }

        String lastTransactionID = transactions.get(transactions.size() - 1).transactionID;
        int numericPart = Integer.parseInt(lastTransactionID.substring(1));
        return String.format("T%04d", numericPart + 1);
    }
    
    //GENERATE NOTIFICATION ID
    public String GenerateNotificationID(List<Notification> notifications) {
        if (notifications.isEmpty()) {
            return "N0001";
        }

        String lastNotificationID = notifications.get(notifications.size() - 1).notificationID;
        int numericPart = Integer.parseInt(lastNotificationID.substring(1));
        return String.format("N%04d", numericPart + 1);
    }
    
    //////////////////////////////VIEW TRANSACTION//////////////////////////////
    //REFRESH TRANSACTION TABLE DATA
    public void LoadTransactionTable(JTable transactionTable) {
        DefaultTableModel tableModel = GetTransactionTableModel();

        transactionTable.setModel(tableModel);
    }

    //TRANSACTION TABLE SEARCH FIELD
    public void TransactionTableSearchField(JTable transactionTable, JTextField searchField){
        String searchText = searchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) transactionTable.getModel());
        transactionTable.setRowSorter(sorter); 
        
        sorter.setRowFilter(searchText.isEmpty() ? null : RowFilter.regexFilter("(?i)" + searchText));

        for (int i = 0; i < transactionTable.getColumnCount(); i++) {
            transactionTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
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
        transactionTable.repaint();
    }

    //TRANSACTION PAGE CLEAR DATA
    public void ViewTransactionPageClearData(JTable transactionTable, JTextField searchField){
        transactionTable.clearSelection();
        searchField.setText("");
        TransactionTableresetTable(transactionTable);
    }
    
    //TRANSACTION TABLE RESET TABLE 
    public void TransactionTableresetTable(JTable transactionTable) {
        TableRowSorter<?> sorter = (TableRowSorter<?>) transactionTable.getRowSorter();
        if (sorter != null) {
            sorter.setRowFilter(null);
        }

        for (int i = 0; i < transactionTable.getColumnCount(); i++) {
            transactionTable.getColumnModel().getColumn(i).setCellRenderer(null);
        }

        transactionTable.repaint(); // 
    }
    
    //UPDATE TRANSACTION TABLE DATA
    public DefaultTableModel GetTransactionTableModel() {
        Type transactionType = new TypeToken<Transaction>() {}.getType();
        FileHandler<Transaction> fileHandler = new FileHandler<>();
        List<Transaction> transactions = fileHandler.readTxt(transactionFilePath, transactionType);
        
        Type userType = new TypeToken<User>() {}.getType();
        FileHandler<User> userfileHandler = new FileHandler<>();
        List<User> users = userfileHandler.readTxt(userFilePath, userType);

        String[] columnNames = {"TransactionID", "CustomerID", "Username", "Amount", "Transacrion Date", "Transaction Time", "Account Balance", "View Receipt"};

        Object[][] tableData = new Object[transactions.size()][columnNames.length];      

        // Populate the table data     
        for (int i = 0; i < transactions.size(); i++) {         
            Transaction transaction = transactions.get(i);          
       
            tableData[i][0] = transaction.transactionID;         
            tableData[i][1] = transaction.customerID;  
            
            String username = "Unknown";
            for (User user : users) {         
                if (user.getUserID().equals(transaction.customerID)) {             
                    username = user.getUsername();             
                    break;
                }         
            }
            
            tableData[i][2] = username;
            tableData[i][3] = transaction.amount;         

            String transactionDate = transaction.getDate();
            String[] dateTimeParts = transactionDate.split(" ");

            tableData[i][4] = dateTimeParts[0];
            tableData[i][5] = dateTimeParts[1]; 

            tableData[i][6] = transaction.accBalance;         
            tableData[i][7] = "<html><a href=''>View</a></html>"; 
        }      
   
        return new DefaultTableModel(tableData, columnNames); 
    }
    
    ///////////////////////////////////RECEIPT//////////////////////////////////
    //VIEW RECEIPT ROW
    public void ViewReceiptRow(String username12, JFrame transaction, JTable transactionTable, java.awt.event.MouseEvent evt) {
        int row = transactionTable.rowAtPoint(evt.getPoint());
        int col = transactionTable.columnAtPoint(evt.getPoint());
        this.username1 = username12;

        if (col == 7) {
            String transactionID = transactionTable.getValueAt(row, 0).toString();
            String customerID = transactionTable.getValueAt(row, 1).toString();
            String username = transactionTable.getValueAt(row, 2).toString();
            String amount = transactionTable.getValueAt(row, 3).toString();
            String transactionDate = transactionTable.getValueAt(row, 4).toString();
            String transactionTime = transactionTable.getValueAt(row, 5).toString();
            String accountBalance = transactionTable.getValueAt(row, 6).toString();

            AdminReceipt adminReceipt = new AdminReceipt(this, true, username1);
            adminReceipt.Receipt(username1, transactionID, customerID, username, amount, transactionDate, transactionTime, accountBalance);
            adminReceipt.setVisible(true);
            transaction.dispose();
        }
    }

    //GENERATE RECEIPT
    public void GenerateReceipt(String username1, JLabel transactionidtxt, JLabel customeridtxt, JLabel usernametxt, JLabel amounttxt, JLabel transactiondatetxt, JLabel transactiontimetxt, JLabel accountbalancetxt,
                                String transactionID, String customerID, String username, String amount, String transactionDate, String transactionTime, String accountBalance){
        transactionidtxt.setText(transactionID);
        customeridtxt.setText(customerID);
        usernametxt.setText(username);
        amounttxt.setText(amount);
        transactiondatetxt.setText(transactionDate);
        transactiontimetxt.setText(transactionTime);
        accountbalancetxt.setText(accountBalance);
    }
    
    //CLOSE RECEIPT
    public void CloseReceipt(JFrame receipt, String username12){
        this.username1 = username12;
        AdminViewTransaction transaction = new AdminViewTransaction(username1);
        receipt.dispose();
        transaction.setVisible(true);     
    }
    
    //OK RECEIPT
    public void OkReceipt(JFrame receipt, String username12){
        this.username1 = username12;
        AdminTopUp topuppage = new AdminTopUp(username1);
        receipt.dispose();
        topuppage.setVisible(true);
    }
    
    public void DownloadReceiptImage(String username1, JFrame receipt, String Username, JButton closeButton, JButton downloadButton, JButton okButton, boolean isTransactionPageView) {
        try {
            JButton buttonToHide = isTransactionPageView ? closeButton : okButton;
            buttonToHide.setVisible(false);
            downloadButton.setVisible(false);
            
            BufferedImage image = new BufferedImage(receipt.getWidth(), receipt.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            receipt.paint(g2d);
            g2d.dispose();

            buttonToHide.setVisible(true);
            downloadButton.setVisible(true);
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
            String timestamp = now.format(formatter);
        
            String username = Username.replaceAll("[^a-zA-Z0-9_-]", "_");
            String fileName = "receipt_" + username + "_" + timestamp + "_.png";
            File file = new File(fileName);

            ImageIO.write(image, "png", file);

            JOptionPane.showMessageDialog(null, "Receipt Saved As: " + file.getAbsolutePath(), "Download Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Saving Image: " + e.getMessage(), "Download Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


