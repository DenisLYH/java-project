/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.google.gson.reflect.TypeToken;
import com.mycompany.testing.Complain;
import com.mycompany.testing.FileHandler;
import com.mycompany.testing.Menu;
import com.mycompany.testing.Order;
import java.util.List;

/**
 *
 * @author georg
 */
public class MF {
    public static List<Complain> getComplain(){
        String complainFilePath = "complains.txt";
        java.lang.reflect.Type complainType = new TypeToken<Complain>() {}.getType();
        FileHandler<Complain> complainFileHandler = new FileHandler<>();
        List<Complain> complainList = complainFileHandler.readTxt(complainFilePath, complainType);
        return complainList;
    }
    public static List<Menu> getMenu(){
        String menuFilePath = "menus.txt";
        java.lang.reflect.Type menuType = new TypeToken<Menu>(){}.getType();
        FileHandler<Menu> menuFile = new FileHandler<>();
        return menuFile.readTxt(menuFilePath, menuType);
    }
    public static List<Order> getOrder(){
        String orderFilePath = "orders.txt";
        java.lang.reflect.Type orderType = new TypeToken<Order>() {}.getType();
        FileHandler<Order> orderFile = new FileHandler<>();
        return orderFile.readTxt(orderFilePath, orderType);
    }
    
    public static void writeComplain(List<Complain> cplist){
        String complainFilePath = "complains.txt";
        FileHandler<Complain> complainFileHandler = new FileHandler<>();
        complainFileHandler.writeTxt(complainFilePath, cplist);
    }
    public static void writeMenu(List<Menu> menuList){
        String menuFilePath = "menus.txt";
        FileHandler<Menu> menuFile = new FileHandler<>();
        menuFile.writeTxt(menuFilePath, menuList);
    }
}
