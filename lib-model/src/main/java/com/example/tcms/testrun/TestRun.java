package com.example.tcms.testrun;

import com.example.tcms.common.Common;
import com.example.tcms.enums.TestRunStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_runs")
@Getter
@Setter
@NoArgsConstructor
public class TestRun extends Common {

    @Column(nullable = false)
    private String name;

    private String projectId;

    private String testSuiteId;

    private String executedBy;

    @Enumerated(EnumType.STRING)
    private TestRunStatus status;

    private int totalTests;

    private int passedTests;

    private int failedTests;

    private int blockedTests;

    private int skippedTests;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;
}
