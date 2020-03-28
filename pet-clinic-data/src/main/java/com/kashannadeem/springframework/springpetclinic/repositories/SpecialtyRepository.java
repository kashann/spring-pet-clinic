package com.kashannadeem.springframework.springpetclinic.repositories;

import com.kashannadeem.springframework.springpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
