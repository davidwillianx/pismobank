package com.example.pismobank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Account {
    @Id
    private Long id;
    private String documentNumber;
}
