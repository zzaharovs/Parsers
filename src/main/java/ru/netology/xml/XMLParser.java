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

        List <Employee> listXML = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            listXML.add(getEmployee(nodeList.item(i)));
        }

        return listXML;
    }

    private static Employee getEmployee(Node node) {

        Employee empl = new Employee();
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            Element element = (Element) node;
            empl.setId(Long.valueOf(getTagValue("id", element)));
            empl.setFirstName(getTagValue("firstName", element));
            empl.setLastName(getTagValue("lastName", element));
            empl.setCountry(getTagValue("country", element));
            empl.setAge(Integer.parseInt(getTagValue("age", element)));

        }
        return empl;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
