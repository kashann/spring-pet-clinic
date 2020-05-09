package com.kashannadeem.springframework.springpetclinic.controllers;

import com.kashannadeem.springframework.springpetclinic.model.Owner;
import com.kashannadeem.springframework.springpetclinic.model.Pet;
import com.kashannadeem.springframework.springpetclinic.model.PetType;
import com.kashannadeem.springframework.springpetclinic.services.PetService;
import com.kashannadeem.springframework.springpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;
    private Owner owner;
    private Set<PetType> petTypes;
    private Pet pet;
    private final Long petId = 1L;
    private final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        owner = Owner.builder()
                .id(ownerId)
                .lastName("Doe")
                .firstName("John")
                .build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        pet = Pet.builder()
                .id(petId)
                .birthDate(LocalDate.of(2006,02,01))
                .name("Matteo")
                .visits(new HashSet<>())
                .owner(owner)
                .petType((PetType) petTypes.toArray()[0])
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/" + ownerId + "/pets/" + petId + "/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }


    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/" + ownerId + "/pets/" + petId + "/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2019-11-11")
                .param("description", "yet another visit")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("redirect:/owners/" + ownerId));

        verify(visitService).save(any());
    }
}