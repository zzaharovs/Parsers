package ru.netology.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.netology.employee.Employee;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public static List<Employee> parceXML(String fileName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("employee");

        List<Employee> listXML = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            listXML.add(getEmployee(nodeList.item(i)));
        }

        return listXML;
    }

    private static Employee getEmployee(Node node) {

        Element element = (Element) node;
        NodeList nodeList = node.getChildNodes();

        String[] fieldsEmployee = new String[nodeList.getLength()];
        String[] valuesEmployee = new String[fieldsEmployee.length];

        int size = 0;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                fieldsEmployee[size] = node_.getNodeName();
                size++;
            }
        }

        for (int i = 0; i < size
                ; i++) {

            NodeList valuesList = element.getElementsByTagName(fieldsEmployee[i])
                    .item(0)
                    .getChildNodes();

            valuesEmployee[i] = valuesList.item(0).getNodeValue();

        }

        return new Employee(
                Long.parseLong(valuesEmployee[0]),
                valuesEmployee[1],
                valuesEmployee[2],
                valuesEmployee[3],
                Integer.parseInt(valuesEmployee[4])
        );
    }

}