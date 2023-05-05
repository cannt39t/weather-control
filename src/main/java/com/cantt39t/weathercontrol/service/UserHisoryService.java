package com.cantt39t.weathercontrol.service;

import com.cantt39t.weathercontrol.dto.UserHistoryCreateDto;
import com.cantt39t.weathercontrol.dto.UserHistoryResponseDto;

public interface UserHisoryService {

    UserHistoryResponseDto create(UserHistoryCreateDto userHistoryCreateDto);

    boolean isRequestLimitExceeded(String email);

}
