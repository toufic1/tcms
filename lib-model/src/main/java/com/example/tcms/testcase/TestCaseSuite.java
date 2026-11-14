package com.example.tcms.testcase;

import com.example.tcms.common.Common;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_suites")
@Getter
@Setter
@NoArgsConstructor
public class TestCaseSuite extends Common {

    @Column(nullable = false)
    private String name;

    private String description;

    private String projectId;

    @ElementCollection
    @CollectionTable(name = "test_suite_cases", joinColumns = @JoinColumn(name = "suite_id"))
    @Column(name = "test_case_id")
    private List<String> testCaseIds = new ArrayList<>();

    private boolean active;
}
