package com.practice.recruitmentservice.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "enrollee")
public class Enrollee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "enrollee_id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "address")
  private String address;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "approved")
  private Boolean approved = false;

  @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.EAGER)
  @JoinColumn(name = "in_recruitment_id", referencedColumnName = "recruitment_id")
  private Recruitment recruitment;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "chosen_specialty", joinColumns = @JoinColumn(name = "s_enrollee_id"))
  @Column(name = "specialty_uid")
  private List<String> chosenSpecialties;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Enrollee enrollee = (Enrollee) o;
    return
        firstName.equals(enrollee.firstName) && lastName.equals(enrollee.lastName) &&
            address.equals(enrollee.address) && birthDate.equals(enrollee.birthDate) &&
            approved.equals(enrollee.approved) && recruitment.equals(enrollee.recruitment) &&
            chosenSpecialties.equals(enrollee.chosenSpecialties);
  }

  @Override
  public int hashCode() {
    int hash = 1;
    hash = !firstName.isEmpty() ? 3 * hash + firstName.hashCode() : hash;
    hash = !lastName.isEmpty() ? 3 * hash + lastName.hashCode() : hash;
    hash = birthDate != null ? 3 * hash + birthDate.hashCode() : hash;
    hash = !address.isEmpty() ? 3 * hash + address.hashCode() : hash;
    hash = approved != null ? 3 * hash + approved.hashCode() : hash;
    hash = recruitment != null ? 3 * hash + recruitment.hashCode() : hash;
    hash = chosenSpecialties != null && !chosenSpecialties.isEmpty() ? 3 * hash + chosenSpecialties.hashCode() : hash;
    return hash;
  }

  @Override public String toString() {
    return "Enrollee{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", approved=" + approved +
        ", recruitment=" + recruitment +
        ", chosenSpecialties=" + chosenSpecialties +
        '}';
  }
}
