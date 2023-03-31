package com.aws.spring;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@Table(name = "table")
public class Inventory {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    private Integer amount;
    private Integer price;
}
