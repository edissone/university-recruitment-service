package com.practice.recruitmentservice.util;

import org.springframework.stereotype.Component;

public interface EntityMapper<E, D> {
  E toEntity(D dto);

  D toDto(E entity);
}
