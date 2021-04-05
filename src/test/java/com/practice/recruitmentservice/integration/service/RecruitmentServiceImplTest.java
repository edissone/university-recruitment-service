package com.practice.recruitmentservice.integration.service;

import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.dao.repository.RecruitmentRepository;
import com.practice.recruitmentservice.dto.RecruitmentDto;
import com.practice.recruitmentservice.integration.BaseIntegrationTest;
import com.practice.recruitmentservice.service.RecruitmentService;
import com.practice.recruitmentservice.util.RecruitmentMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecruitmentServiceImplTest extends BaseIntegrationTest {
  @Autowired
  RecruitmentRepository repository;
  @Autowired
  RecruitmentMapper mapper;
  @Autowired
  private RecruitmentService service;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @DisplayName("Successful getting actual recruitment")
  @ParameterizedTest
  @MethodSource(
      "com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments"
  )
  void getSuccessful(Recruitment recruitment) {
    Long id = repository.save(recruitment).getId();
    RecruitmentDto expect = mapper.toDto(recruitment);
    RecruitmentDto actual = service.get(id);

    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(expect.getStartDate(), actual.getStartDate()),
        () -> assertEquals(expect.getEndDate(), actual.getEndDate())
    );
  }

  @DisplayName("Successful getting actual recruitment")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments")
  void getActualSuccessful(Recruitment recruitment) {
    repository.save(recruitment);
    RecruitmentDto expect = mapper.toDto(recruitment);
    RecruitmentDto actual = service.getActual();

    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(expect.getStartDate(), actual.getStartDate()),
        () -> assertEquals(expect.getEndDate(), actual.getEndDate())
    );
  }

  @DisplayName("Failed getting actual recruitment")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideNotCurrentDateRecruitments")
  void getActualFailed(Recruitment recruitment) {
    repository.save(recruitment);

    RecruitmentDto actual = service.getActual();

    assertNull(actual);
  }

  @DisplayName("Successful creating recruitment")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments")
  void createSuccessful(Recruitment recruitment) {
    RecruitmentDto expect = mapper.toDto(recruitment);
    RecruitmentDto actual = service.create(expect);

    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(expect.getStartDate(), actual.getStartDate()),
        () -> assertEquals(expect.getEndDate(), actual.getEndDate())
    );
  }

  @DisplayName("Failed creating recruitment: empty dto")
  @Test
  void createFailed_emptyDto() {
    //fixme
    assertThrows(RuntimeException.class, () -> service.create(RecruitmentDto.builder().build()));
  }

  @DisplayName("Successful update recruitment")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideUpdatedCurrentDateRecruitments")
  void updateSuccessful(Map<String, Recruitment> recruitment) {
    Long id = repository.save(recruitment.get("initial")).getId();

    RecruitmentDto expect = mapper.toDto(recruitment.get("updated"));
    RecruitmentDto actual = service.update(id, expect);

    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(id, actual.getId()),
        () -> assertEquals(expect.getStartDate(), actual.getStartDate()),
        () -> assertEquals(expect.getEndDate(), actual.getEndDate())
    );
  }

  @DisplayName("Failed updating recruitment: not found")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments")
  void updateFailed_notFound(Recruitment recruitment) {

    assertThrows(EntityNotFoundException.class, () -> service.update(recruitment.getId(),mapper.toDto(recruitment)));
  }

  @DisplayName("Success deleting recruitment")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments")
  void deleteSuccessful(Recruitment recruitment) {
    Long id = repository.save(recruitment).getId();

    RecruitmentDto expect = mapper.toDto(recruitment);
    RecruitmentDto actual = service.delete(id);

    assertAll(
        () -> assertNotNull(actual),
        () -> assertEquals(id, actual.getId()),
        () -> assertEquals(expect.getStartDate(), actual.getStartDate()),
        () -> assertEquals(expect.getEndDate(), actual.getEndDate())
    );
  }

  @DisplayName("Failed deleting recruitment: not found")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.RecruitmentArgumentProvider#provideCurrentDateRecruitments")
  void deleteFailed_notFound(Recruitment recruitment) {

    assertThrows(EntityNotFoundException.class, () -> service.delete(recruitment.getId()));
  }


}