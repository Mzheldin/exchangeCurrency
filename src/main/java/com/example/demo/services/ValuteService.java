package com.example.demo.services;

import com.example.demo.entities.Valute;

import java.util.List;

public interface ValuteService {
    Valute save(Valute valute);
    void saveAll(List<Valute> valutes);
    List<Valute> findAll();
    Valute findById(String id);
}
