package com.jinro.webide.login.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SignupResponseDto {
    private HttpStatus status;
    private boolean result;
    private String message;
}
