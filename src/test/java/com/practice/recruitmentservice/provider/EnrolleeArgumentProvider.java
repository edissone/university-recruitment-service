package com.practice.recruitmentservice.provider;

import com.practice.recruitmentservice.dao.model.Enrollee;
import com.practice.recruitmentservice.dao.model.Recruitment;
import io.bloco.faker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

public final class EnrolleeArgumentProvider {
  private static final Faker faker = new Faker("en");

  public static Stream<Arguments> provideEnrollees() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 10; i++) {
      result = Stream.concat(result,
          Stream.of(
              Arguments.of(
                  Enrollee.builder()
                      .firstName(faker.name.firstName())
                      .lastName(faker.name.lastName())
                      .birthDate(getRandomBirthDate(16, 18))
                      .address(faker.address.streetAddress())
                      .approved(false)
                      .chosenSpecialties(
                          List.of(
                              "spec:".concat(UUID.randomUUID().toString()),
                              "spec:".concat(UUID.randomUUID().toString())
                          )
                      )
                      .build()
              )
          )
      );
    }

    return result;
  }

  public static Stream<Arguments> provideNotEqual() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 25; i++) {
      result = Stream.concat(result, Stream.of(Arguments.of(generate(), generate())));
    }

    return result;
  }

  public static Stream<Arguments> provideEnrolleesWithRecruitment() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 3; i++) {
      result = Stream.concat(result,
          Stream.of(
              Arguments.of(
                  List.of(generate(), generate(), generate()),
                  Recruitment.builder()
                      .startDate(LocalDate.now().minusDays(i + new Random().nextInt((5 - 1) + 1)))
                      .endDate(LocalDate.now().plusDays(i + new Random().nextInt((5 - 1) + 1)))
                      .build()
              )
          )
      );
    }
    return result;
  }

  public static Stream<Arguments> provideEqual() {
    Stream<Arguments> result = Stream.of();
    for (int i = 1; i <= 3; i++) {
      String firstName = faker.name.firstName();
      String lastName = faker.name.lastName();
      LocalDate birthDate = getRandomBirthDate(16, 18);
      String address = faker.address.streetAddress();
      List<String> specialties = List.of(
          "spec:".concat(UUID.randomUUID().toString()),
          "spec:".concat(UUID.randomUUID().toString())
      );

      result = Stream.concat(result,
          Stream.of(
              Arguments.of(
                  Enrollee.builder()
                      .firstName(firstName)
                      .lastName(lastName)
                      .birthDate(birthDate)
                      .address(address)
                      .approved(false)
                      .chosenSpecialties(specialties).build(),
                  Enrollee.builder()
                      .firstName(firstName)
                      .lastName(lastName)
                      .birthDate(birthDate)
                      .address(address)
                      .approved(false)
                      .chosenSpecialties(specialties).build()
              )
          )
      );
    }
    return result;
  }

  private static Enrollee generate() {
    return Enrollee.builder()
        .firstName(faker.name.firstName())
        .lastName(faker.name.lastName())
        .birthDate(getRandomBirthDate(16, 18))
        .address(faker.address.streetAddress())
        .approved(false)
        .chosenSpecialties(
            List.of(
                "spec:".concat(UUID.randomUUID().toString()),
                "spec:".concat(UUID.randomUUID().toString())
            )
        )
        .build();
  }

  private static LocalDate getRandomBirthDate(int minAge, int maxAge) {
    return LocalDate.ofInstant(faker.date.birthday(-minAge, -maxAge).toInstant(), ZoneId.systemDefault());
  }
}
