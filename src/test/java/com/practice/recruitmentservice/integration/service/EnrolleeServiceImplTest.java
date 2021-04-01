package com.practice.recruitmentservice.integration.service;

import com.practice.recruitmentservice.dao.model.Enrollee;
import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.dao.repository.EnrolleeRepository;
import com.practice.recruitmentservice.dao.repository.RecruitmentRepository;
import com.practice.recruitmentservice.dto.enrollee.EnrolleeDto;
import com.practice.recruitmentservice.integration.BaseIntegrationTest;
import com.practice.recruitmentservice.service.EnrolleeService;
import com.practice.recruitmentservice.util.EnrolleeMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnrolleeServiceImplTest extends BaseIntegrationTest {

  @Autowired
  EnrolleeMapper mapper;

  @Autowired
  EnrolleeService service;

  @Autowired
  EnrolleeRepository repository;

  @Autowired
  RecruitmentRepository recruitmentRepository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
    recruitmentRepository.deleteAll();
  }

  @DisplayName("Success get all enrollee")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.EnrolleeArgumentProvider#provideEnrolleesWithRecruitment")
  void getAllSuccess(List<Enrollee> enrollees, Recruitment recruitment) {
    recruitmentRepository.save(recruitment);
    repository.saveAll(enrollees.stream().peek(e -> e.setRecruitment(recruitment)).collect(Collectors.toList()));

    List<EnrolleeDto> expect = enrollees.stream().map(mapper::toDto).collect(Collectors.toList());

    List<EnrolleeDto> actual = service.getAll();

    expect.sort(Comparator.comparing(EnrolleeDto::getId));
    actual.sort(Comparator.comparing(EnrolleeDto::getId));

    assertAll(
        () -> assertNotNull(actual),
        () -> assertThat(actual, is(not(empty()))),
        () -> assertEquals(expect.size(), actual.size()),
        () -> assertIterableEquals(expect, actual)
    );
  }

  @Test
  void getAllFromRecruitment() {
  }

  @Test
  void get() {
  }

  @Test
  void create() {
  }

  @Test
  void update() {
  }

  @Test
  void testUpdate() {
  }

  @Test
  void delete() {
  }
}