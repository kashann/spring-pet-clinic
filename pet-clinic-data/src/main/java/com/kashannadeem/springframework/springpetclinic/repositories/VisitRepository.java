package com.kashannadeem.springframework.springpetclinic.repositories;

import com.kashannadeem.springframework.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
