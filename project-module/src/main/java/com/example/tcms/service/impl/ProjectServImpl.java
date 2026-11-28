package com.example.tcms.service.impl;

import com.example.tcms.dto.request.CreateProjectRequest;
import com.example.tcms.dto.request.UpdateProjectRequest;
import com.example.tcms.dto.response.ProjectResponse;
import com.example.tcms.exceptions.DuplicateException;
import com.example.tcms.exceptions.NotFoundException;
import com.example.tcms.exceptions.ProjectAlreadyArchivedException;
import com.example.tcms.mapper.impl.ProjectMapperImpl;
import com.example.tcms.project.Project;
import com.example.tcms.repository.ProjectRepository;
import com.example.tcms.repository.UserRepository;
import com.example.tcms.service.ProjectServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServImpl implements ProjectServ {
    private static final Logger log = LoggerFactory.getLogger(ProjectServImpl.class);
    private ProjectRepository repository;
    private UserRepository userRepository;
    private ProjectMapperImpl projectMapper;

    public ProjectServImpl(ProjectRepository repository, UserRepository userRepository, ProjectMapperImpl projectMapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponse create(CreateProjectRequest request, String userId) {
        if (repository.existsByName(request.name())) {
            throw new DuplicateException("Project exists");
        }
        List<String> memberIds = request.memberIds();

        checkMemberExist(memberIds);
        if (!userRepository.findByIdAndIsDeletedFalse(userId).isPresent()) {
            throw new NotFoundException("Owner Not Found");
        }
        Project project = projectMapper.toEntity(request);
        project.setCreatedBy(userId);
        repository.save(project);
        log.info("project has been saved successfully");
        return projectMapper.toResponse(project);
    }

    @Override
    public ProjectResponse getById(String id) {
        Project project = repository.findByIdAndArchivedFalse(id)
                .orElseThrow(()-> new NotFoundException("Project Not Found"));
        return projectMapper.toResponse(project);
    }

    @Override
    public List<ProjectResponse> getAll() {
        List<Project> projects = repository.findByArchivedFalse();
        return projects.stream().map(projectMapper::toResponse).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ProjectResponse update(String id, UpdateProjectRequest request) {
        Project project = repository.findByIdAndArchivedFalse(id)
                .orElseThrow(()-> new NotFoundException("Project Not Found"));
        List<String> memberIds = request.getMemberIds();
        checkMemberExist(memberIds);
        projectMapper.update(request, project);
        repository.save(project);
        return projectMapper.toResponse(project);
    }

    @Override
    public void archive(String id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found")
                );
        if (project.isArchived()) {
            throw new ProjectAlreadyArchivedException("Project Already archived");
        }
        project.setArchived(true);
        repository.save(project);

    }

    private void checkMemberExist(List<String> memberIds) {
        if (memberIds == null) {
            return;
        } else {
            for (String id : memberIds) {
                if (!userRepository.findByIdAndIsDeletedFalse(id).isPresent()) {
                    throw new NotFoundException("User Id not found: " + id);
                }
            }
        }
    }
}
