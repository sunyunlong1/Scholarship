package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;

@Data
public class AdminDtoResponse {


    private List<AdminTable> adminTableList;
}
