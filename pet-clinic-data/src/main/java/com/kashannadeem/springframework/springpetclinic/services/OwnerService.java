package com.kashannadeem.springframework.springpetclinic.services;

import com.kashannadeem.springframework.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
