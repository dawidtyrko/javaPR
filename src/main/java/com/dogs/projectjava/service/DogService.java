package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;


public class DogService {
   // @Value("${dog.api.url}")
    //private String apiUrl;

   // @Value("${dog.api.key}")
    //private String apiKey;

    public Optional<Dog> getDogByName(String dogName) {
        Dog dog = new Dog();
        Optional<Dog> dogg = null;
        try {
            String urlPrep = "https://api.api-ninjas.com/v1/dogs?name=";
            //String dogName = "labrador"; // Replace with the desired dog name
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
                Type listType = new TypeToken<List<Dog>>() {
                }.getType();
                // Parse JSON response using Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                Gson gson = new Gson();
                List<Dog> dogs = gson.fromJson(content.toString(), listType);
                //JsonNode jsonNode = objectMapper.readTree(content.toString());
                // dog = objectMapper.readValue(content.toString(), Dog.class);
                // Now you can work with the JSON data using the JsonNode object
                // System.out.println("Dog Info: " + jsonNode);
                for (Dog dog1 : dogs) {
                    System.out.println(dog1.getName());
                }
                dogg = dogs.stream().findFirst();

            } else {
                System.out.println("Failed to fetch data. HTTP error code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dogg;
    }
}
