package com.scholarship.demo.api;

import lombok.Data;

@Data
public class ChangeDto {

    private String account;
    private String role;
    private String oldPassword;
    private String newPassword;
    private String vCode;
}
