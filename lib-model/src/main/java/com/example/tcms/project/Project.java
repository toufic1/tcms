package com.example.tcms.project;

import com.example.tcms.common.Common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project extends Common {

    private String name;

    private String description;

    private String createdBy;

    private List<String> memberIds;

    private boolean archived;

    private String version;

}