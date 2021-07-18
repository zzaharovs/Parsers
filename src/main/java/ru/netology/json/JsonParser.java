package ru.netology.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.netology.employee.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static String readString(String path) {

        String json = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((json = br.readLine()) != null) {

                return json;

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(json);
        return json;

    }

    public static List<Employee> jsonToList(String json) {

        List<Employee> list = new ArrayList<>();

        try {

            Object obj = new JSONParser().parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object o : jsonArray) {

                JSONObject jo = (JSONObject) o;

                Employee empl = gson.fromJson(jo.toJSONString(), Employee.class);
                list.add(empl);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

}
