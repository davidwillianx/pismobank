package com.example.pismobank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table
public class OperationType {
    @Id
    private Long id;
    private String description;
}
