package com.cantt39t.weathercontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class WeatherControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherControlApplication.class, args);
    }

}
