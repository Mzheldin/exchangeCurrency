package com.example.demo.services;

import com.example.demo.entities.Rate;
import com.example.demo.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private RateRepository rateRepository;

    @Autowired
    public void setRateRepository(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }


    @Override
    @Transactional
    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    @Transactional
    public void saveAll(List<Rate> rates) {
        rateRepository.saveAll(rates);
    }

    @Override
    @Transactional
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    @Transactional
    public Rate findOneByValuteIdAndCreatedAt(String valuteId, Date createdAt) {
        return rateRepository.findOneByValuteIdAndCreatedAt(valuteId, createdAt);
    }
}
