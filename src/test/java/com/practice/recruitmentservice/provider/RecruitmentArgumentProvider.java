package com.practice.recruitmentservice.provider;

import com.practice.recruitmentservice.dao.model.Recruitment;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class RecruitmentArgumentProvider {

  public static Stream<? extends Arguments> provideCurrentDateRecruitments() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 10; i++) {
      result = Stream.concat(result, Stream.of(
          Arguments.of(
              Recruitment.builder()
                  .id(Integer.toUnsignedLong(i))
                  .startDate(LocalDate.now().minusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .endDate(LocalDate.now().plusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .build()
          )
          )
      );
    }

    return result;
  }

  public static Stream<? extends Arguments> provideNotCurrentDateRecruitments() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 10; i++) {
      result = Stream.concat(result, Stream.of(
          Arguments.of(
              Recruitment.builder()
                  .id(Integer.toUnsignedLong(i))
                  .startDate(LocalDate.now().minusYears(i))
                  .endDate(LocalDate.now().plusDays(i + new Random().nextInt((5 - 1) + 1)).minusYears(i))
                  .build()
          )
          )
      );
    }

    return result;
  }

  public static Stream<? extends Arguments> provideUpdatedCurrentDateRecruitments() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 10; i++) {
      result = Stream.concat(result, Stream.of(
          Map.of(
              "initial", Recruitment.builder()
                  .id(Integer.toUnsignedLong(i))
                  .startDate(LocalDate.now().minusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .endDate(LocalDate.now().plusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .build(),
              "updated", Recruitment.builder()
                  .id(Integer.toUnsignedLong(i))
                  .startDate(LocalDate.now().minusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .endDate(LocalDate.now().plusDays(i + new Random().nextInt((5 - 1) + 1)))
                  .build())
      )
          .map(Arguments::of));
    }

    return result;
  }


  public static Recruitment generate(){
    return Recruitment.builder()
        .startDate(LocalDate.now().minusDays(new Random().nextInt((5 - 1) + 1)))
        .endDate(LocalDate.now().plusDays(new Random().nextInt((5 - 1) + 1)))
        .build();
  }
}
