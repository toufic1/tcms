package com.example.tcms.testrun;

import com.example.tcms.common.Common;
import com.example.tcms.enums.ResultStatus;
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
@Table(name = "test_results")
@Getter
@Setter
@NoArgsConstructor
public class TestResult extends Common {

    private String testRunId;

    private String testCaseId;

    @Enumerated(EnumType.STRING)
    private ResultStatus result;

    @Column(length = 4000)
    private String actualResult;

    @Column(length = 4000)
    private String comments;

    private String executedBy;

    private long executionTimeInSeconds;

    @ElementCollection
    @CollectionTable(name = "test_result_attachments", joinColumns = @JoinColumn(name = "test_result_id"))
    @Column(name = "attachment")
    private List<String> attachments = new ArrayList<>();
}
