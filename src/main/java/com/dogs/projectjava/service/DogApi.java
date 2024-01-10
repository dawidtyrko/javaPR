package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.Dog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class DogApi {


    public Dog getDogByName(String dogName) {
        Dog dawg = new Dog();
        try {
            String urlPrep = "https://api.api-ninjas.com/v1/dogs?name=";
            String apiKey = "IWSSMJcs6KDl10HMPDHNiA==JyBlVCJUqzdWiKEd";
            URL url = new URL(urlPrep + dogName);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-key", apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                List<Dog> dogs = objectMapper.readValue(content.toString(), new TypeReference<List<Dog>>() {});

                if(!dogs.isEmpty()){
                    dawg = dogs.get(0);
                    System.out.println(dawg);
                }
                System.out.println(dawg.getImageLink());

            } else {
                System.out.println("Failed to fetch data. HTTP error code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dawg;
    }
}
