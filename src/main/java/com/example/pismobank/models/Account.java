package com.example.pismobank.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode
@Setter
@Getter
@Table
@Entity
public class Account {
    @Id
    private Long id;
    private String documentNumber;
}
