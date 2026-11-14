package com.example.tcms.testcase;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable = not an entity with its own id; it only exists as part of a TestCase
 * (stored as rows in the test_case_steps table).
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestStep {

    // "order" is a reserved SQL word (ORDER BY), so the column gets another name
    @Column(name = "step_order")
    private int order;

    private String action;

    private String expectedResult;
}
