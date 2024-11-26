package com.testproject;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ParserJSON {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File("GreenhouseGasEmissions.json"));

            JsonNode emissionsNode = rootNode.path("Emissions");
            int count = 0;
            for (JsonNode emission : emissionsNode) {
                String category = emission.path("Category").asText();
                String gasUnits = emission.path("Gas Units").asText();
                double value = emission.path("Value").asDouble();

                if (value > 0){
                System.out.println("Category: " + category);
                System.out.println("Gas Units: " + gasUnits);
                System.out.println("Value: " + value);
                System.out.println();
                count++;

                }


            }

            System.out.println("Total number of objects parsed: " + count);

            JsonNode dataNode = rootNode.path("data");
            String date = dataNode.path("date").asText();
            String country = dataNode.path("country").asText();

            System.out.println("Date: " + date);
            System.out.println("Country: " + country);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}