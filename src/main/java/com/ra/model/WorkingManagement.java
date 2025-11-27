package com.ra.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "working", uniqueConstraints = @UniqueConstraint(columnNames = "working_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkingManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manage_id")
    private Integer manageId;

    @Column(name = "working_name", length = 100, nullable = false, unique = true)
    private String workingName;

    @Column(name = "person_name", length = 50, nullable = false)
    private String personName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "duration", nullable = false)
    private Float duration;

    @Column(name = "duration_unit", length = 20, nullable = false)
    private String durationUnit;

    @Column(name = "working_description", length = 255, nullable = false)
    private String workingDescription;

    @Column(name = "working_progress", nullable = false, length = 50)
    private String workingProgress;

    @Column(name = "working_image", length = 255)
    private String workingImage;

    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 1")
    private Integer status;
}
