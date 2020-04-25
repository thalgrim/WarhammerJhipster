package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.WarhammerJhipsterApp;
import com.thalgrim.warhammer.domain.Personnage;
import com.thalgrim.warhammer.repository.PersonnageRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonnageResource} REST controller.
 */
@SpringBootTest(classes = WarhammerJhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PersonnageResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private PersonnageRepository personnageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonnageMockMvc;

    private Personnage personnage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnage createEntity(EntityManager em) {
        Personnage personnage = new Personnage()
            .nom(DEFAULT_NOM);
        return personnage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Personnage createUpdatedEntity(EntityManager em) {
        Personnage personnage = new Personnage()
            .nom(UPDATED_NOM);
        return personnage;
    }

    @BeforeEach
    public void initTest() {
        personnage = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonnage() throws Exception {
        int databaseSizeBeforeCreate = personnageRepository.findAll().size();

        // Create the Personnage
        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isCreated());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeCreate + 1);
        Personnage testPersonnage = personnageList.get(personnageList.size() - 1);
        assertThat(testPersonnage.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    public void createPersonnageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personnageRepository.findAll().size();

        // Create the Personnage with an existing ID
        personnage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonnageMockMvc.perform(post("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonnages() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        // Get all the personnageList
        restPersonnageMockMvc.perform(get("/api/personnages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personnage.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)));
    }
    
    @Test
    @Transactional
    public void getPersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        // Get the personnage
        restPersonnageMockMvc.perform(get("/api/personnages/{id}", personnage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personnage.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM));
    }

    @Test
    @Transactional
    public void getNonExistingPersonnage() throws Exception {
        // Get the personnage
        restPersonnageMockMvc.perform(get("/api/personnages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        int databaseSizeBeforeUpdate = personnageRepository.findAll().size();

        // Update the personnage
        Personnage updatedPersonnage = personnageRepository.findById(personnage.getId()).get();
        // Disconnect from session so that the updates on updatedPersonnage are not directly saved in db
        em.detach(updatedPersonnage);
        updatedPersonnage
            .nom(UPDATED_NOM);

        restPersonnageMockMvc.perform(put("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonnage)))
            .andExpect(status().isOk());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeUpdate);
        Personnage testPersonnage = personnageList.get(personnageList.size() - 1);
        assertThat(testPersonnage.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonnage() throws Exception {
        int databaseSizeBeforeUpdate = personnageRepository.findAll().size();

        // Create the Personnage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonnageMockMvc.perform(put("/api/personnages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnage)))
            .andExpect(status().isBadRequest());

        // Validate the Personnage in the database
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonnage() throws Exception {
        // Initialize the database
        personnageRepository.saveAndFlush(personnage);

        int databaseSizeBeforeDelete = personnageRepository.findAll().size();

        // Delete the personnage
        restPersonnageMockMvc.perform(delete("/api/personnages/{id}", personnage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Personnage> personnageList = personnageRepository.findAll();
        assertThat(personnageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
