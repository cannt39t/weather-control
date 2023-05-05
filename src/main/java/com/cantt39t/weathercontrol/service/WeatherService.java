package com.cantt39t.weathercontrol.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WeatherService {

    private static final String API_KEY = "6faded995f07a67bd6431a5176bb4640";
    private static final ObjectMapper mapper = new ObjectMapper();

    public Double getTemperature(String city) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(connection.getInputStream())
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }

                JsonNode node = mapper.readTree(content.toString());
                String temperatureString = node.get("main").get("temp").asText();
                return Double.parseDouble(temperatureString) - 273.15;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
