package com.example.tcms.service;

import com.example.tcms.dto.request.CreateProjectRequest;
import com.example.tcms.dto.request.UpdateProjectRequest;
import com.example.tcms.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectServ {
    ProjectResponse create(CreateProjectRequest request,String id);

    ProjectResponse getById(String id);

    List<ProjectResponse> getAll();

    ProjectResponse update(String id, UpdateProjectRequest request);

    void archive(String id);
}
