package com.practice.recruitmentservice.service;

import com.practice.recruitmentservice.dto.RecruitmentDto;

import java.util.List;

public interface RecruitmentService {
  List<RecruitmentDto> list();

  RecruitmentDto get(Long id);

  RecruitmentDto getActual();

  RecruitmentDto create(RecruitmentDto dto);

  RecruitmentDto update(Long id, RecruitmentDto dto);

  RecruitmentDto delete(Long id);
}
