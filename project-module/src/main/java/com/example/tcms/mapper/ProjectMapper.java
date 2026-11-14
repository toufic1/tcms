package com.example.tcms.mapper;

import com.example.tcms.dto.request.CreateProjectRequest;
import com.example.tcms.dto.request.UpdateProjectRequest;
import com.example.tcms.dto.response.ProjectResponse;
import com.example.tcms.project.Project;
import com.example.tcms.user.User;

public interface ProjectMapper {
    Project toEntity(CreateProjectRequest request);

    ProjectResponse toResponse(Project project);

    void update(UpdateProjectRequest request, Project project);
}
