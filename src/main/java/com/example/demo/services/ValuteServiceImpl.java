package com.example.demo.services;

import com.example.demo.entities.Valute;
import com.example.demo.repositories.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ValuteServiceImpl implements ValuteService {

    private ValuteRepository valuteRepository;

    @Autowired
    public void setValuteRepository(ValuteRepository valuteRepository) {
        this.valuteRepository = valuteRepository;
    }

    @Override
    @Transactional
    public Valute save(Valute valute) {
        return valuteRepository.save(valute);
    }

    @Override
    @Transactional
    public void saveAll(List<Valute> valutes) {
        valuteRepository.saveAll(valutes);
    }

    @Override
    @Transactional
    public List<Valute> findAll() {
        return valuteRepository.findAll();
    }

    @Override
    @Transactional
    public Valute findById(String id) {
        return valuteRepository.findById(id);
    }
}
