package com.example.tcms.common;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Fields every table shares.
 * MappedSuperclass = not a table itself; its fields become columns in each child entity's table.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Common {

    // PostgreSQL generates nothing here: Hibernate creates a random UUID before inserting.
    // Kept as String so the existing services/DTOs (which use String ids) keep working.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Filled automatically by Hibernate on insert / on every update (no extra config needed,
    // unlike Mongo's @CreatedDate which needed auditing enabled and was always null here).
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
