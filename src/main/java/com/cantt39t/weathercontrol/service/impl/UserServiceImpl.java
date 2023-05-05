package com.cantt39t.weathercontrol.service.impl;

import com.cantt39t.weathercontrol.dto.CreateUserRequestDto;
import com.cantt39t.weathercontrol.dto.UserResponseDto;
import com.cantt39t.weathercontrol.model.User;
import com.cantt39t.weathercontrol.repository.UserRepository;
import com.cantt39t.weathercontrol.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserResponseDto create(CreateUserRequestDto createUserDto) {
        String encodedPassword = encoder.encode(createUserDto.getPassword());
        User user = User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .password(encodedPassword)
                .build();
        userRepository.save(user);
        return UserResponseDto.fromEntity(user);
    }
}
