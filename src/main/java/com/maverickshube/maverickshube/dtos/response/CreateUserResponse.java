package com.maverickshube.maverickshube.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponse {
    private Long id;
    private String email;
    private String message;
}
