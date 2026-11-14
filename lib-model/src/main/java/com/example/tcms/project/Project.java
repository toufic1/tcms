package com.example.tcms.project;

import com.example.tcms.common.Common;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends Common {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private String createdBy;

    // Mongo stored this list inside the document. A SQL column holds ONE value, so the list
    // lives in a second table: project_members(project_id, member_id), one row per member.
    // EAGER = load the members together with the project (the mapper always needs them).
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "member_id")
    private List<String> memberIds = new ArrayList<>();

    private boolean archived;

    private String version;

}
