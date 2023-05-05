package com.cantt39t.weathercontrol.controller;

import com.cantt39t.weathercontrol.dto.CreateUserRequestDto;
import com.cantt39t.weathercontrol.dto.UserHistoryCreateDto;
import com.cantt39t.weathercontrol.dto.UserResponseDto;
import com.cantt39t.weathercontrol.model.User;
import com.cantt39t.weathercontrol.service.UserHisoryService;
import com.cantt39t.weathercontrol.service.UserService;
import com.cantt39t.weathercontrol.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final UserHisoryService userHisoryService;
    private final WeatherService weatherService;

    @PostMapping("sign_up")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        UserResponseDto userResponseDto = userService.create(createUserRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("get_weather")
    public ResponseEntity<Double> getWeather(@RequestParam String city) {
        Double temp = weatherService.getTemperature(city);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        if (!userHisoryService.isRequestLimitExceeded(email)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        userHisoryService.create(
                UserHistoryCreateDto.builder()
                        .email(email)
                        .city(city)
                        .temp(temp)
                        .timestamp(new Date())
                        .build()
        );

        return ResponseEntity.ok(temp);
    }

    @GetMapping("my_data")
    public String getMyData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        return "Hello, " + email;
    }

}

