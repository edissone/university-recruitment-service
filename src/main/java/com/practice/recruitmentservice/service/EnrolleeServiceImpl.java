package com.practice.recruitmentservice.service;

import com.practice.recruitmentservice.dao.model.Enrollee;
import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.dao.repository.EnrolleeRepository;
import com.practice.recruitmentservice.dao.repository.RecruitmentRepository;
import com.practice.recruitmentservice.dto.enrollee.EnrolleeDto;
import com.practice.recruitmentservice.exception.NoActualRecruitmentException;
import com.practice.recruitmentservice.util.EnrolleeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {
  private final RecruitmentRepository recruitmentRepository;
  private final EnrolleeRepository repository;
  private final EnrolleeMapper mapper;

  @Autowired
  public EnrolleeServiceImpl(
      RecruitmentRepository recruitmentRepository,
      EnrolleeRepository repository, EnrolleeMapper mapper) {
    this.recruitmentRepository = recruitmentRepository;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override public List<EnrolleeDto> getAll() {
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override public List<EnrolleeDto> getAllFromRecruitment(Long recruitmentId) {
    return repository.findAllByRecruitment_Id(recruitmentId).stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override public EnrolleeDto get(Long id) {
    return repository.findById(id).map(mapper::toDto).orElseThrow(EntityNotFoundException::new);
  }

  @Override public EnrolleeDto create(EnrolleeDto dto) {
    Recruitment recruitment = recruitmentRepository.findActual(LocalDate.now()).orElseThrow(NoActualRecruitmentException::new);
    Enrollee entity = mapper.toEntity(dto);
    entity.setRecruitment(recruitment);
    entity.setApproved(false);
    entity.setChosenSpecialties(dto.getChosenSpecialties());
    Enrollee result = repository.save(entity);
    return mapper.toDto(result);
  }

  @Override public EnrolleeDto update(EnrolleeDto dto) {
    Enrollee toUpdate = getIfExist(dto);
    return mapper.toDto(repository.save(updateFields(toUpdate, dto)));
  }

  @Override public EnrolleeDto update(Long id, EnrolleeDto dto) {
    Enrollee toUpdate = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return mapper.toDto(repository.save(updateFields(toUpdate, dto)));
  }

  @Override public EnrolleeDto delete(Long id) {
    Enrollee result = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    ;
    repository.delete(result);
    return mapper.toDto(result);
  }

  private Enrollee getIfExist(EnrolleeDto dto) throws EntityNotFoundException {
    return repository
        .findByFirstNameAndLastName(
            dto.getFirstName().toUpperCase(Locale.ROOT),
            dto.getLastName().toUpperCase(Locale.ROOT)
        )
        .orElseThrow(EntityNotFoundException::new);
  }

  private Enrollee updateFields(Enrollee toUpdate, EnrolleeDto dto) {
    toUpdate.setAddress(dto.getAddress() != null ? dto.getAddress() : toUpdate.getAddress());
    toUpdate.setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : toUpdate.getBirthDate());
    toUpdate.setAddress(dto.getLastName() != null ? dto.getLastName() : toUpdate.getLastName());
    toUpdate.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : toUpdate.getFirstName());
    toUpdate.setChosenSpecialties(
        dto.getChosenSpecialties() != null ? dto.getChosenSpecialties() : toUpdate.getChosenSpecialties());
    return toUpdate;
  }
}
