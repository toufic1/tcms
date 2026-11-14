package com.example.tcms.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProjectRequest {
    String name;

    String description;

    List<String> memberIds;
}
