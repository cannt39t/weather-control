package com.cantt39t.weathercontrol.controller;

import com.cantt39t.weathercontrol.dto.CreateUserRequestDto;
import com.cantt39t.weathercontrol.dto.UserResponseDto;
import com.cantt39t.weathercontrol.model.User;
import com.cantt39t.weathercontrol.service.UserService;
import com.cantt39t.weathercontrol.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final WeatherService weatherService;

    @PostMapping("sign_up")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        UserResponseDto userResponseDto = userService.create(createUserRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("get_weather")
    public ResponseEntity<Double> getWeather(@RequestParam String city) {
        Double temp = weatherService.getTemperature(city);
        return ResponseEntity.ok(temp);
    }

}

