package com.practice.recruitmentservice.dao.repository;

import com.practice.recruitmentservice.dao.model.Enrollee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EnrolleeRepository extends CrudRepository<Enrollee, Long> {
  List<Enrollee> findAllByRecruitment_Id(Long id);

  List<Enrollee> findAll();

  Optional<Enrollee> findByFirstNameAndLastName(String firstName, String lastName);
}
