package com.kashannadeem.springframework.springpetclinic.controllers;

import com.kashannadeem.springframework.springpetclinic.model.Pet;
import com.kashannadeem.springframework.springpetclinic.model.Visit;
import com.kashannadeem.springframework.springpetclinic.services.PetService;
import com.kashannadeem.springframework.springpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm or processNewVisitForm is called
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model) {
        return VIEWS_PETS_CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, @PathVariable Long ownerId, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_VISIT_FORM;
        } else {
            visitService.save(visit);

            return "redirect:/owners/" + ownerId;
        }
    }
}
