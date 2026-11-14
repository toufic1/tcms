package com.example.tcms.testcase;

import com.example.tcms.common.Common;
import com.example.tcms.enums.Priority;
import com.example.tcms.enums.TestCaseStatus;

import java.util.List;

public class TestCase extends Common {
    String title;

    String description;

    String preconditions;

    List<TestStep> steps;

    String expectedResult;

    Priority priority;

    TestCaseStatus status;

    String projectId;

    String testSuiteId;

    String createdBy;

    String assignedTo;

    List<String> tags;

    String automationStatus;

    String automationScript;

    String moduleName;

    boolean deleted;
}
