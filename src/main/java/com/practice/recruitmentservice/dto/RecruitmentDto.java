package com.practice.recruitmentservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentDto {
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @NotNull
  private LocalDate startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @NotNull
  private LocalDate endDate;
  private List<Long> enrolleeIds;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    RecruitmentDto that = (RecruitmentDto) o;
    return startDate.equals(that.startDate) && endDate.equals(that.endDate);
  }

  @Override
  public int hashCode() {
    int hash = 1;
    hash = startDate != null ? 7 * hash + startDate.hashCode() : hash;
    hash = endDate != null ? 7 * hash + endDate.hashCode() : hash;
    return hash;
  }
}
