package com.example.tcms.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateProjectRequest {
    String name;

    String description;

    boolean archived;

    List<String> memberIds;
}
