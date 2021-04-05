package com.practice.recruitmentservice.util;

import com.practice.recruitmentservice.dao.model.Enrollee;
import com.practice.recruitmentservice.dto.enrollee.EnrolleeDto;
import org.springframework.stereotype.Component;

@Component
public class EnrolleeMapper implements EntityMapper<Enrollee, EnrolleeDto> {
  @Override public Enrollee toEntity(EnrolleeDto dto) {
    return Enrollee.builder()
        //.id(Optional.ofNullable(dto.getId()).orElse(null))
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .address(dto.getAddress())
        .birthDate(dto.getBirthDate())
        .chosenSpecialties(dto.getChosenSpecialties())
        .build();
  }

  @Override public EnrolleeDto toDto(Enrollee entity) {
    return EnrolleeDto.builder()
        .id(entity.getId())
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .address(entity.getAddress())
        .birthDate(entity.getBirthDate())
        .chosenSpecialties(entity.getChosenSpecialties())
        .approved(entity.getApproved())
        .recruitmentId(entity.getRecruitment().getId())
        .build();
  }
}
