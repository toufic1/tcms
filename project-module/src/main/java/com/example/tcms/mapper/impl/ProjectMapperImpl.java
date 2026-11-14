package com.example.tcms.mapper.impl;

import com.example.tcms.dto.request.CreateProjectRequest;
import com.example.tcms.dto.request.UpdateProjectRequest;
import com.example.tcms.dto.response.ProjectResponse;
import com.example.tcms.mapper.ProjectMapper;
import com.example.tcms.project.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Project toEntity(CreateProjectRequest request) {

        Project project = new Project();

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setMemberIds(request.getMemberIds());
        project.setArchived(false);
        project.setVersion("v1.0");

        return project;
    }

    @Override
    public ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdBy(project.getCreatedBy())
                .archived(project.isArchived())
                .version(project.getVersion())
                .memberIds(project.getMemberIds())
                .createdAt(project.getCreatedAt())
                .build();
    }

    @Override
    public void update(UpdateProjectRequest request, Project project) {

        if (request.getName() != null) {
            project.setName(request.getName());
        }

        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
        }

        if (request.getMemberIds() != null) {
            project.setMemberIds(request.getMemberIds());
        }

        project.setArchived(request.isArchived());
    }
}
