package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "valutes")
@Data
@NoArgsConstructor
public class Valute implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "num_code")
    private Integer numCode;

    @Column(name = "char_code")
    private String charCode;

    @Column(name = "name")
    private String name;

    public Valute(String id, Integer numCode, String charCode, String name) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
    }
}
