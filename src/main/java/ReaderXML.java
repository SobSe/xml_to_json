import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReaderXML {
    public List<Employee> parseXML(String fileName)  {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
        List<Employee> list = new ArrayList<>();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            Node root = document.getDocumentElement();
            NodeList nodeListEmployees = root.getChildNodes();
            for (int i = 0; i < nodeListEmployees.getLength(); i++) {
                Node node = nodeListEmployees.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element employee = (Element) node;
                    Employee employeeObject = new Employee();
                    NodeList nodeListEmployee = employee.getChildNodes();
                    long id = 0;
                    String firstName = null;
                    String lastName = null;
                    String country = null;
                    int age = 0;
                    for (int j = 0; j < nodeListEmployee.getLength(); j++) {
                        Node nodeEmployee = nodeListEmployee.item(j);
                        if (nodeEmployee.getNodeType() == Node.ELEMENT_NODE) {
                            Element dataEmployee = (Element) nodeEmployee;
                            String tagName = dataEmployee.getTagName();
                            switch (tagName) {
                                case "id" : id = Long.parseLong(dataEmployee.getTextContent()); break;
                                case "firstName" : firstName = dataEmployee.getTextContent(); break;
                                case "lastName" : lastName = dataEmployee.getTextContent(); break;
                                case "country" : country = dataEmployee.getTextContent(); break;
                                case "age" : age = Integer.parseInt(dataEmployee.getTextContent()); break;
                            }
                            /*try {
                                Field field = employeeObject.getClass().getField(dataEmployee.getTagName());
                                field.set(employeeObject, dataEmployee.getTextContent());
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                System.out.println(e.getMessage());
                            }*/
                        }
                    }
                    list.add(new Employee(id, firstName, lastName, country, age));
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
