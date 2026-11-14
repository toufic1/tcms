package com.example.tcms.testcase;

import com.example.tcms.common.Common;
import com.example.tcms.enums.Priority;
import com.example.tcms.enums.TestCaseStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_cases")
@Getter
@Setter
@NoArgsConstructor
public class TestCase extends Common {

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String description;

    @Column(length = 4000)
    private String preconditions;

    @ElementCollection
    @CollectionTable(name = "test_case_steps", joinColumns = @JoinColumn(name = "test_case_id"))
    private List<TestStep> steps = new ArrayList<>();

    @Column(length = 4000)
    private String expectedResult;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private TestCaseStatus status;

    private String projectId;

    private String testSuiteId;

    private String createdBy;

    private String assignedTo;

    @ElementCollection
    @CollectionTable(name = "test_case_tags", joinColumns = @JoinColumn(name = "test_case_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    private String automationStatus;

    private String automationScript;

    private String moduleName;

    private boolean deleted;
}
