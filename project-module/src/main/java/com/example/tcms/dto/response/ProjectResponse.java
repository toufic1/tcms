package com.example.tcms.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Getter
@Setter
public class ProjectResponse {
    String id;

    String name;

    String description;

    String createdBy;

    boolean archived;

    String version;

    List<String> memberIds;

    LocalDateTime createdAt;
}
