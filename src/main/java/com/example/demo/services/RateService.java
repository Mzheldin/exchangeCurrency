package com.example.demo.services;

import com.example.demo.entities.Rate;

import java.sql.Date;
import java.util.List;

public interface RateService {
    Rate save(Rate rate);
    void saveAll(List<Rate> rates);
    List<Rate> findAll();
    Rate findOneByValuteIdAndCreatedAt(String valuteId, Date createdAt);
}
