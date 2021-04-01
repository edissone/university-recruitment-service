package com.practice.recruitmentservice.dto.enrollee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrolleeDto {
  private Long id;
  @NotNull private String firstName;
  @NotNull private String lastName;
  private String address;
  @NotNull private LocalDate birthDate;
  private Boolean approved;
  private Long recruitmentId;
  @NotNull private List<String> chosenSpecialties;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EnrolleeDto that = (EnrolleeDto) o;
    return firstName.equals(that.firstName) && lastName.equals(that.lastName) &&
        address.equals(that.address) && birthDate.equals(that.birthDate) && approved.equals(that.approved) &&
        Objects.equals(recruitmentId, that.recruitmentId);// && chosenSpecialties.equals(that.chosenSpecialties);
  }
  //fixme chosen specialties hashcode
  @Override
  public int hashCode() {
    int hash = 1;
    hash = !firstName.isEmpty() ? 3 * hash + firstName.hashCode() : hash;
    hash = !lastName.isEmpty() ? 3 * hash + lastName.hashCode() : hash;
    hash = birthDate != null ? 3 * hash + birthDate.hashCode() : hash;
    hash = !address.isEmpty() ? 3 * hash + address.hashCode() : hash;
    hash = approved != null ? 3 * hash + approved.hashCode() : hash;
    hash = recruitmentId != null ? 3 * hash + recruitmentId.hashCode() : hash;
//    hash = chosenSpecialties != null && !chosenSpecialties.isEmpty() ? 3 * hash + chosenSpecialties.hashCode() : hash;
    return hash;
  }

  @Override public String toString() {
    return "EnrolleeDto{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", approved=" + approved +
        ", recruitmentId=" + recruitmentId +
        ", chosenSpecialties=" + chosenSpecialties +
        '}';
  }
}
