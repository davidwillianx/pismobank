package com.example.pismobank.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class OperationTypes {
    private Long id;
    private String description;
}
