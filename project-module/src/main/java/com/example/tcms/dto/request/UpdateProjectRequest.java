package com.example.tcms.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateProjectRequest {
    @Size(min = 1, max = 255)
    String name;

    @Size(max = 255)
    String description;


    boolean archived;

    List<String> memberIds;
}
