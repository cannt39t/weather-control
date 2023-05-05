package com.cantt39t.weathercontrol.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    private String email;
    private String password;

}
