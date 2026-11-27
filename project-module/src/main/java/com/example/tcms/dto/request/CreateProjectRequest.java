package com.example.tcms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

public record CreateProjectRequest(
        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 255)
        String description,

        List<String> memberIds) {
}
