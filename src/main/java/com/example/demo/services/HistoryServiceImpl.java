package com.example.demo.services;

import com.example.demo.entities.History;
import com.example.demo.entities.Rate;
import com.example.demo.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository historyRepository;
    private RateService rateService;
    private XMLParseBean xmlParseBean;

    @Autowired
    public void setHistoryRepository(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Autowired
    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }

    @Autowired
    public void setXmlParseBean(XMLParseBean xmlParseBean) {
        this.xmlParseBean = xmlParseBean;
    }

    @Override
    @Transactional
    public History save(History history){
        return historyRepository.save(history);
    }

    @Override
    @Transactional
    public List<History> saveAll(List<History> histories){
        return historyRepository.saveAll(histories);
    }

    @Override
    @Transactional
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public History exchangeUpdateHistory(History history) {
        int i = 1;
        Rate sourceRate = rateService.findOneByValuteIdAndCreatedAt(history.getSourceCurrency(), Date.valueOf(LocalDate.now().minus(1, ChronoUnit.DAYS)));
        Rate targetRate = rateService.findOneByValuteIdAndCreatedAt(history.getTargetCurrency(), Date.valueOf(LocalDate.now().minus(1, ChronoUnit.DAYS)));
        while (sourceRate == null || targetRate == null){
            xmlParseBean.parseValCurs();
            sourceRate = rateService.findOneByValuteIdAndCreatedAt(history.getSourceCurrency(), Date.valueOf(LocalDate.now().minus(i, ChronoUnit.DAYS)));
            targetRate = rateService.findOneByValuteIdAndCreatedAt(history.getTargetCurrency(), Date.valueOf(LocalDate.now().minus(i, ChronoUnit.DAYS)));
            i++;
        }
        BigDecimal sourceArgument = sourceRate.getValue().divide(new BigDecimal(sourceRate.getNominal()));
        BigDecimal targetArgument = targetRate.getValue().divide(new BigDecimal(targetRate.getNominal()));
        history.setResultSumm(sourceArgument.divide(targetArgument, 4, RoundingMode.HALF_UP));
        return history;
    }

    @Override
    @Transactional
    public History findOneById(Long id) {
        return historyRepository.findById(id).isPresent() ? historyRepository.findById(id).get() : null;
    }

    @Override
    public History checkLastHistory(String lastHistoryId) {
        History history = new History();
        if (lastHistoryId != null) {
            History lastHistory = findOneById(Long.valueOf(lastHistoryId));
            if (lastHistory != null) {
                history.setSourceCurrency(lastHistory.getSourceCurrency());
                history.setTargetCurrency(lastHistory.getTargetCurrency());
                history.setSourceSumm(lastHistory.getSourceSumm());
                history.setResultSumm(lastHistory.getResultSumm());
            }
        }
        return history;
    }

    @Override
    @Transactional
    public List<History> findAllByDateAndSourceAndTargetValutes(Date date, String sourceValute, String targetValute) {
        return historyRepository.findAllByCreatedAtAndAndSourceCurrencyAndTargetCurrency(date, sourceValute, targetValute);
    }
}
