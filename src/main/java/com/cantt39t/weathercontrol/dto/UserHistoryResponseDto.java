package com.cantt39t.weathercontrol.dto;

import com.cantt39t.weathercontrol.model.User;
import com.cantt39t.weathercontrol.model.UserHistory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryResponseDto {

    private Integer id;

    private String email;

    private String city;

    private Double temp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date timestamp;

    public static UserHistoryResponseDto fromEntity(UserHistory userHistory) {
        return UserHistoryResponseDto.builder()
                .id(userHistory.getId())
                .email(userHistory.getEmail())
                .city(userHistory.getCity())
                .temp(userHistory.getTemp())
                .build();
    }

}

