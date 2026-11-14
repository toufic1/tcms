package com.example.tcms.testrun;

import com.example.tcms.common.Common;
import com.example.tcms.enums.TestRunStatus;

import java.time.LocalDateTime;

public class TestRun extends Common {
    String name;

    String projectId;

    String testSuiteId;

    String executedBy;

    TestRunStatus status;

    int totalTests;

    int passedTests;

    int failedTests;

    int blockedTests;

    int skippedTests;

    LocalDateTime startedAt;

    LocalDateTime completedAt;
}
