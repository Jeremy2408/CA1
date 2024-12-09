package com.testproject;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParserJSON {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File("GreenhouseGasEmissions.json"));

            JsonNode emissionsNode = rootNode.path("Emissions");

            String url = "jdbc:mysql://localhost:3306/springdb";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);

            String updateSQL = "UPDATE emissions SET actual_value = ? WHERE category = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);

            for (JsonNode emission : emissionsNode) {
                String category = emission.path("Category").asText();
                double value = emission.path("Value").asDouble();

                if (value > 0) {
                    pstmt.setDouble(1, value);
                    pstmt.setString(2, category);
                    int rowsUpdated = pstmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Updated database: Category=" + category + ", Actual Value=" + value);
                    }
                }
            }


            pstmt.close();
            conn.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}