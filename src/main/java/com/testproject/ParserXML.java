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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        try {
            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/springdb";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);

            String insertSQL = "INSERT INTO emissions (year, scenario, predicted_value,category,gas_units) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            for (int i = 0; i < rowList.getLength(); i++) {
                Node rowNode = rowList.item(i);

                if (rowNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) rowNode;

                    String year = elem.getElementsByTagName("Year").item(0).getTextContent();
                    String scenario = elem.getElementsByTagName("Scenario").item(0).getTextContent();
                    String valueStr = elem.getElementsByTagName("Value").item(0).getTextContent();
                    String category = elem.getElementsByTagName("Category__1_3").item(0).getTextContent();
                    String gas_units = elem.getElementsByTagName("Gas___Units").item(0).getTextContent();

                    if (year.equals("2023") && scenario.equals("WEM") && !valueStr.isEmpty()) {
                        double value = Double.parseDouble(valueStr);
                        if (value > 0) {
                            count++;

                            pstmt.setInt(1, Integer.parseInt(year));
                            pstmt.setString(2, scenario);
                            pstmt.setDouble(3, value);
                            pstmt.setString(4, category);
                            pstmt.setString(5, gas_units);
                            pstmt.executeUpdate();

                        
                            System.out.println("Added to database!" );


                        }
                    }
                }
            }

            System.out.println("Total entries added to database: " + count);

            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}