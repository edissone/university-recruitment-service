package com.practice.recruitmentservice.model;

import com.practice.recruitmentservice.BaseTest;
import com.practice.recruitmentservice.dao.model.Enrollee;
import com.practice.recruitmentservice.dao.model.Recruitment;
import com.practice.recruitmentservice.provider.RecruitmentArgumentProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EnrolleeTest extends BaseTest {
  @DisplayName("Equal hashcode 2 Enrollee")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.EnrolleeArgumentProvider#provideEqual")
  void enrolleeHashcode_equal(Enrollee first, Enrollee second){
    assertEquals(first.hashCode(), second.hashCode());
  }

  @DisplayName("Equal 2 Enrollee")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.EnrolleeArgumentProvider#provideEqual")
  void enrollee_equal(Enrollee first, Enrollee second){
    Recruitment recruitment = RecruitmentArgumentProvider.generate();
    first.setRecruitment(recruitment);
    second.setRecruitment(recruitment);
    assertEquals(second, first);
  }

  @DisplayName("Not equal hashcode 2 Enrollee")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.EnrolleeArgumentProvider#provideNotEqual")
  void enrolleeHashcode_notEqual(Enrollee first, Enrollee second){
    assertNotEquals(first.hashCode(), second.hashCode());
  }

  @DisplayName("Not equal 2 Enrollee")
  @ParameterizedTest
  @MethodSource("com.practice.recruitmentservice.provider.EnrolleeArgumentProvider#provideNotEqual")
  void enrollee_notEqual(Enrollee first, Enrollee second){
    Recruitment recruitment = RecruitmentArgumentProvider.generate();
    first.setRecruitment(recruitment);
    second.setRecruitment(recruitment);
    assertNotEquals(second, first);
  }
}
