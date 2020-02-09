package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "history")
@Data
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "source_summ")
    private BigDecimal sourceSumm;

    @Column(name = "result_summ")
    private BigDecimal resultSumm;

    @Column(name = "created_at")
    private Date createdAt;

    public History(String sourceCurrency, String targetCurrency, BigDecimal sourceSumm, BigDecimal resultSumm) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceSumm = sourceSumm;
        this.resultSumm = resultSumm;
        this.createdAt = Date.valueOf(LocalDate.now());
    }

    public History() {
        this.createdAt = Date.valueOf(LocalDate.now());
    }
}
