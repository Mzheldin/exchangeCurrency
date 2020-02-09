package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "rates")
@IdClass(RateId.class)
@Data
@NoArgsConstructor
public class Rate implements Serializable {

    @Column(name = "nominal")
    private Integer nominal;

    @Column(name = "valute_value")
    private BigDecimal value;

    @Id
    @Column(name = "created_at")
    private java.sql.Date createdAt;

    @Id
    @Column(name = "valute_id")
    private String valuteId;

    public Rate(Integer nominal, BigDecimal value, String valuteId, Date createdAt) {
        this.nominal = nominal;
        this.value = value;
        this.valuteId = valuteId;
        this.createdAt = createdAt;
    }
}
