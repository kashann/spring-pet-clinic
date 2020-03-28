package com.kashannadeem.springframework.springpetclinic.repositories;

import com.kashannadeem.springframework.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
