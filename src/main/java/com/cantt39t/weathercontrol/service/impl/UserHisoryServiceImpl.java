package com.cantt39t.weathercontrol.service.impl;

import com.cantt39t.weathercontrol.dto.UserHistoryCreateDto;
import com.cantt39t.weathercontrol.dto.UserHistoryResponseDto;
import com.cantt39t.weathercontrol.model.UserHistory;
import com.cantt39t.weathercontrol.repository.UserHistoryRepository;
import com.cantt39t.weathercontrol.repository.UserRepository;
import com.cantt39t.weathercontrol.service.UserHisoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserHisoryServiceImpl implements UserHisoryService {

    private final UserHistoryRepository userHistoryRepository;

    @Override
    public UserHistoryResponseDto create(UserHistoryCreateDto userHistoryCreateDto) {
        UserHistory userHistory = UserHistory.builder()
                .email(userHistoryCreateDto.getEmail())
                .city(userHistoryCreateDto.getCity())
                .temp(userHistoryCreateDto.getTemp())
                .timestamp(userHistoryCreateDto.getTimestamp())
                .build();
        userHistoryRepository.save(userHistory);
        return UserHistoryResponseDto.fromEntity(userHistory);
    }


    public boolean isRequestLimitExceeded(String email) {
        return userHistoryRepository.canUserAccessData(email);
    }
}
