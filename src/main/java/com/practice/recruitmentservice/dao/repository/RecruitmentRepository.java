package com.practice.recruitmentservice.dao.repository;

import com.practice.recruitmentservice.dao.model.Recruitment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends CrudRepository<Recruitment, Long> {
  @Query(value = "select r from Recruitment as r where ?1 between r.startDate and r.endDate")
  Optional<Recruitment> findActual(LocalDate date);

  List<Recruitment> findAll();

  @Query(value = "select (count(r) > 0)" +
      "  from Recruitment as r" +
      "  where ?1 between r.startDate and r.endDate OR" +
      "  ?2 between r.startDate and r.endDate OR" +
      "  r.startDate between ?1 and ?2 OR" +
      "  r.endDate between ?1 and ?2")
  Boolean anyActual(LocalDate start, LocalDate end);
}
