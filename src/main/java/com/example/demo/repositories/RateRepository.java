package com.example.demo.repositories;

import com.example.demo.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findAllByCreatedAt(Date date);
    Rate findOneByValuteIdAndCreatedAt(String valuteId, Date createdAt);
}
