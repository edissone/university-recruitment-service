package com.practice.recruitmentservice.util;

import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.dto.RecruitmentDto;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentMapper implements EntityMapper<Recruitment, RecruitmentDto> {
  @Override public Recruitment toEntity(RecruitmentDto dto) {
    return Recruitment.builder()
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .build();
  }

  @Override public RecruitmentDto toDto(Recruitment entity) {
    return RecruitmentDto.builder()
        .id(entity.getId())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .build();
  }
}