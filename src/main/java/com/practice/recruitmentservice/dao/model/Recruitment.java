package com.practice.recruitmentservice.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recruitment")
public class Recruitment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recruitment_id")
  private Long id;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = {
//          CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.REFRESH,
      },
      orphanRemoval = true,
      mappedBy = "recruitment")
  private List<Enrollee> enrollee;

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Recruitment that = (Recruitment) o;
    return startDate.equals(that.startDate) && endDate.equals(that.endDate);
  }

  @Override
  public int hashCode() {
    int hash = 1;
    hash = startDate != null ? 7 * hash + startDate.hashCode() : hash;
    hash = endDate != null ? 7 * hash + endDate.hashCode() : hash;
    return hash;
  }

  @Override
  public String toString() {
    return "Recruitment{" +
        "id=" + id +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}

