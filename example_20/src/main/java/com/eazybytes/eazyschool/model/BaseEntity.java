package com.eazybytes.eazyschool.model;

import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@Data
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
