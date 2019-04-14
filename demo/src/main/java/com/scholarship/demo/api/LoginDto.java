package com.scholarship.demo.api;

import lombok.Data;

@Data
public class LoginDto {
    private String account;
    private String password;
    private String role;
}
