package com.practice.recruitmentservice.service;

import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.dao.repository.RecruitmentRepository;
import com.practice.recruitmentservice.dto.RecruitmentDto;
import com.practice.recruitmentservice.exception.RecruitmentIsAlreadyStartedException;
import com.practice.recruitmentservice.util.RecruitmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
  private final RecruitmentMapper mapper;
  private final RecruitmentRepository repository;

  @Autowired
  public RecruitmentServiceImpl(RecruitmentMapper mapper,
      RecruitmentRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override public List<RecruitmentDto> list() {
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override public RecruitmentDto get(Long id) {
    return repository.findById(id).map(mapper::toDto).orElseThrow(EntityNotFoundException::new);
  }

  @Override public RecruitmentDto getActual() {
    return repository.findActual(LocalDate.now()).map(mapper::toDto).orElse(null);
  }

  @Override public RecruitmentDto create(RecruitmentDto dto) {
    if (repository.anyActual(dto.getStartDate(), dto.getEndDate()))
      throw new RecruitmentIsAlreadyStartedException();
    return mapper.toDto(repository.save(mapper.toEntity(dto)));
  }

  @Override public RecruitmentDto update(Long id, RecruitmentDto dto) {
    Recruitment toUpdate = repository.findById(id).orElseThrow(EntityNotFoundException::new);

    toUpdate.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : toUpdate.getStartDate());
    toUpdate.setEndDate(dto.getEndDate() != null ? dto.getEndDate() : toUpdate.getEndDate());

    return mapper.toDto(repository.save(toUpdate));
  }

  @Override public RecruitmentDto delete(Long id) {
    Recruitment result = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    repository.delete(result);
    return mapper.toDto(result);
  }
}
