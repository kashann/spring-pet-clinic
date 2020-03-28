package com.kashannadeem.springframework.springpetclinic.repositories;

import com.kashannadeem.springframework.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
