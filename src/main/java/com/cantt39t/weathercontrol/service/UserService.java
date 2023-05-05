package com.cantt39t.weathercontrol.service;

import com.cantt39t.weathercontrol.dto.CreateUserRequestDto;
import com.cantt39t.weathercontrol.dto.UserResponseDto;

public interface UserService {

    UserResponseDto create(CreateUserRequestDto createUserDto);
}
