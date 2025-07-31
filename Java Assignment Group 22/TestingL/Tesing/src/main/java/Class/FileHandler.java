/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author georg
 */
public class FileHandler<T> {
    public void writeTxt(String filePath, List<T> objects) {
        Gson gson = new Gson();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T object : objects) {
                String json = gson.toJson(object);
                writer.write(json + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取 TXT 文件
    public List<T> readTxt(String filePath,Type type) {
        Gson gson = new Gson();
        ArrayList objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T object = gson.fromJson(line, type);
                objects.add(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
