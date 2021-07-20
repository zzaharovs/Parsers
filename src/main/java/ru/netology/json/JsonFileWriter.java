package ru.netology.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.netology.employee.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonFileWriter {

    public static String listToJson(List<Employee> list) {


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        return gson.toJson(list, listType);

    }

    public static void writeString(String json, String path) {

        try (FileWriter file = new
                FileWriter(path)) {
            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
