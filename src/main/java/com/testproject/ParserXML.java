package com.testproject;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ParserXML {

    public static void main(String argv[]) throws
            IOException, ParserConfigurationException, SAXException {

        File xmlFile = new File("emissions.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList rowList = doc.getElementsByTagName("Row");
        int count = 0;

        for (int i = 0; i < rowList.getLength(); i++) {

            Node rowNode = rowList.item(i);

            if (rowNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) rowNode;

                String year = elem.getElementsByTagName("Year").item(0).getTextContent();
                String scenario = elem.getElementsByTagName("Scenario").item(0).getTextContent();
                String valueStr = elem.getElementsByTagName("Value").item(0).getTextContent();

                if (year.equals("2023") && scenario.equals("WEM") && !valueStr.isEmpty()) {
                    double value = Double.parseDouble(valueStr);
                    if (value > 0) {
                        count++;
                    }
                }
            }
        }

        System.out.println("Total entries: " + count);
    }
}