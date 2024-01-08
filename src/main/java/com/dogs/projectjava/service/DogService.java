package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;


public class DogService {


    public Optional<Dog> getDogByName(String dogName) {
        Dog dog = new Dog();
        Optional<Dog> dogg = Optional.empty();
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
                Type listType = new TypeToken<List<Dog>>() {
                }.getType();

                ObjectMapper objectMapper = new ObjectMapper();
                Gson gson = new Gson();
                List<Dog> dogs = gson.fromJson(content.toString(), listType);

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
