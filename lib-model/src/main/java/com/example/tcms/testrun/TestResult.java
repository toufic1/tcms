package com.example.tcms.testrun;

import com.example.tcms.common.Common;
import com.example.tcms.enums.ResultStatus;

import java.util.List;

public class TestResult extends Common {
    String testRunId;

    String testCaseId;

    ResultStatus result;

    String actualResult;

    String comments;

    String executedBy;

    long executionTimeInSeconds;

    List<String> attachments;
}
