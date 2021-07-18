package ru.netology;

import org.xml.sax.SAXException;
import ru.netology.employee.Employee;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static ru.netology.csv.CSVParser.*;
import static ru.netology.json.JsonFileWriter.*;
import static ru.netology.json.JsonParser.*;
import static ru.netology.xml.XMLParser.*;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        List<Employee> listCsv = parceCSV(columnMapping, fileName);
        List<Employee> listXml  = parceXML("data.xml");

        try {


            String json = listToJson(listCsv);
            writeString(json, "data.json");

            String json2 = listToJson(listXml);
            writeString(json2, "data2.json");

            String json3 = readString("data.json");
            List<Employee> list3 = jsonToList(json3);

            list3.forEach(System.out::println);


        } catch (NullPointerException ex) {

            System.out.println("В метод передан пустой список");

        }

    }

}
