package com.kashannadeem.springframework.springpetclinic.repositories;

import com.kashannadeem.springframework.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
