package com.example.demo.services;

import com.example.demo.entities.History;

import java.sql.Date;
import java.util.List;

public interface HistoryService {
    History save(History history);
    List<History> saveAll(List<History> histories);
    List<History> findAll();
    History exchangeUpdateHistory(History history);
    History findOneById(Long id);
    History checkLastHistory(String lastHistoryId);
    List<History> findAllByDateAndSourceAndTargetValutes(Date date, String sourceValute, String targetValute);
}
