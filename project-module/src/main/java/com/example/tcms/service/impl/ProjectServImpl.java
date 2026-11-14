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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectServImpl implements ProjectServ {
    private static final Logger log = LoggerFactory.getLogger(ProjectServImpl.class);
    private ProjectRepository repository;
    private UserRepository userRepository;
    private ProjectMapperImpl projectMapper;

    public ProjectServImpl(ProjectRepository repository, UserRepository userRepository, ProjectMapperImpl projectMapper) {
        this.repository = repository;
        this.userRepository=userRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponse create(CreateProjectRequest request,String userId) {
        if(repository.existsByName(request.getName())){
            throw new DuplicateException("Project exists");
        }
        List<String> memberIds=request.getMemberIds();
        for(String id:memberIds){
            if(!userRepository.findByIdAndIsDeletedFalse(id).isPresent()){
                throw new NotFoundException("User not found "+id);
            }
        }
        if(!userRepository.findByIdAndIsDeletedFalse(userId).isPresent()){
            throw new NotFoundException("Owner Not Found");
        }
        Project project=projectMapper.toEntity(request);
        project.setCreatedBy(userId);
        repository.save(project);
        log.info("project has been saved successfully");
        return projectMapper.toResponse(project);
    }

    @Override
    public ProjectResponse getById(String id) {
        Optional<Project> project = repository.findByIdAndArchivedFalse(id);
        if (!project.isPresent()) {
            log.info("Project Not Found {}", id);
            throw new NotFoundException("Project Not Found");
        }
        return projectMapper.toResponse(project.get());
    }

    @Override
    public List<ProjectResponse> getAll() {
        List<Project> projects = repository.findByArchivedFalse();
        return projects.stream().map(projectMapper::toResponse).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ProjectResponse update(String id, UpdateProjectRequest request) {
        Optional<Project> project=repository.findByIdAndArchivedFalse(id);
        if(!project.isPresent()){
            throw new NotFoundException("Project not found");
        }
        List<String> memberIds=request.getMemberIds();
        for(String uid:memberIds){
            if(!userRepository.findByIdAndIsDeletedFalse(uid).isPresent()){
                throw new NotFoundException("User not found "+id);
            }
        }
        Project pr=project.get();
        projectMapper.update(request,pr);
        repository.save(pr);
        return projectMapper.toResponse(pr);
    }

    @Override
    public void archive(String id) {
        Optional<Project> project = repository.findByIdAndArchivedFalse(id);
        if (!project.isPresent()) {
            log.info("Project not found {}", id);
            throw new NotFoundException("Project not found");
        }
        if(project.get().isArchived()){
            throw new ProjectAlreadyArchivedException("Project Already archived");
        }
        project.get().setArchived(true);
        repository.save(project.get());
    }
}
