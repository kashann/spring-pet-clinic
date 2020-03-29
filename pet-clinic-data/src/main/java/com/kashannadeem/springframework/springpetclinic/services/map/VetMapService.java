package com.kashannadeem.springframework.springpetclinic.services.map;

import com.kashannadeem.springframework.springpetclinic.model.Specialty;
import com.kashannadeem.springframework.springpetclinic.model.Vet;
import com.kashannadeem.springframework.springpetclinic.services.SpecialtyService;
import com.kashannadeem.springframework.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if(object != null) {
            if(object.getSpecialties().size() > 0) {
                object.getSpecialties().forEach(speciality -> {
                    if(speciality.getId() == null) {
                        Specialty savedSpecialty = specialtyService.save(speciality);
                        speciality.setId(savedSpecialty.getId());
                    }
                });
            }
            return super.save(object);
        }
        else
            return null;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
