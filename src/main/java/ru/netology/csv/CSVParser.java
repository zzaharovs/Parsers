package ru.netology.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.netology.employee.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVParser {

    public static List<Employee> parceCSV(String[] columnMapping, String fileName) {


        List<Employee> staff = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {


            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();

            strategy.setType(Employee.class);

            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();


            staff = csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return staff;
    }

}
