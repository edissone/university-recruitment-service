package com.practice.recruitmentservice.service;

import com.practice.recruitmentservice.dto.enrollee.EnrolleeDto;

import java.util.List;

public interface EnrolleeService {
  List<EnrolleeDto> getAll();

  List<EnrolleeDto> getAllFromRecruitment(Long recruitmentId);

  EnrolleeDto get(Long id);

  EnrolleeDto create(EnrolleeDto dto);

  EnrolleeDto update(EnrolleeDto dto);

  EnrolleeDto update(Long id, EnrolleeDto dto);

  EnrolleeDto delete(Long id);
}
