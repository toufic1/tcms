package com.example.tcms.controller;

import com.example.tcms.dto.request.CreateProjectRequest;
import com.example.tcms.dto.request.UpdateProjectRequest;
import com.example.tcms.response.ApiResponse;
import com.example.tcms.service.impl.ProjectServImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectServImpl serv;

    @PostMapping
    public ResponseEntity<?> createProject(@Valid  @RequestBody CreateProjectRequest createProjectRequest, @RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(serv.create(createProjectRequest,userId),
                "Project Created Successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(serv.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(serv.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") String id, @Valid @RequestBody UpdateProjectRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(serv.update(id,request),"Updated Successfully"));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity archiveProject(@PathVariable("id") String id) {
        serv.archive(id);
        return ResponseEntity.noContent().build();
    }
}
