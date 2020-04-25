package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.WarhammerJhipsterApp;
import com.thalgrim.warhammer.domain.Competence;
import com.thalgrim.warhammer.repository.CompetenceRepository;

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
 * Integration tests for the {@link CompetenceResource} REST controller.
 */
@SpringBootTest(classes = WarhammerJhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CompetenceResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_CARACTERISTIQUE = "AAAAAAAAAA";
    private static final String UPDATED_CARACTERISTIQUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DE_BASE = false;
    private static final Boolean UPDATED_DE_BASE = true;

    private static final Boolean DEFAULT_GROUPEE = false;
    private static final Boolean UPDATED_GROUPEE = true;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompetenceMockMvc;

    private Competence competence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Competence createEntity(EntityManager em) {
        Competence competence = new Competence()
            .nom(DEFAULT_NOM)
            .caracteristique(DEFAULT_CARACTERISTIQUE)
            .deBase(DEFAULT_DE_BASE)
            .groupee(DEFAULT_GROUPEE);
        return competence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Competence createUpdatedEntity(EntityManager em) {
        Competence competence = new Competence()
            .nom(UPDATED_NOM)
            .caracteristique(UPDATED_CARACTERISTIQUE)
            .deBase(UPDATED_DE_BASE)
            .groupee(UPDATED_GROUPEE);
        return competence;
    }

    @BeforeEach
    public void initTest() {
        competence = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompetence() throws Exception {
        int databaseSizeBeforeCreate = competenceRepository.findAll().size();

        // Create the Competence
        restCompetenceMockMvc.perform(post("/api/competences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competence)))
            .andExpect(status().isCreated());

        // Validate the Competence in the database
        List<Competence> competenceList = competenceRepository.findAll();
        assertThat(competenceList).hasSize(databaseSizeBeforeCreate + 1);
        Competence testCompetence = competenceList.get(competenceList.size() - 1);
        assertThat(testCompetence.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testCompetence.getCaracteristique()).isEqualTo(DEFAULT_CARACTERISTIQUE);
        assertThat(testCompetence.isDeBase()).isEqualTo(DEFAULT_DE_BASE);
        assertThat(testCompetence.isGroupee()).isEqualTo(DEFAULT_GROUPEE);
    }

    @Test
    @Transactional
    public void createCompetenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = competenceRepository.findAll().size();

        // Create the Competence with an existing ID
        competence.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetenceMockMvc.perform(post("/api/competences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competence)))
            .andExpect(status().isBadRequest());

        // Validate the Competence in the database
        List<Competence> competenceList = competenceRepository.findAll();
        assertThat(competenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCompetences() throws Exception {
        // Initialize the database
        competenceRepository.saveAndFlush(competence);

        // Get all the competenceList
        restCompetenceMockMvc.perform(get("/api/competences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competence.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].caracteristique").value(hasItem(DEFAULT_CARACTERISTIQUE)))
            .andExpect(jsonPath("$.[*].deBase").value(hasItem(DEFAULT_DE_BASE.booleanValue())))
            .andExpect(jsonPath("$.[*].groupee").value(hasItem(DEFAULT_GROUPEE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCompetence() throws Exception {
        // Initialize the database
        competenceRepository.saveAndFlush(competence);

        // Get the competence
        restCompetenceMockMvc.perform(get("/api/competences/{id}", competence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(competence.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.caracteristique").value(DEFAULT_CARACTERISTIQUE))
            .andExpect(jsonPath("$.deBase").value(DEFAULT_DE_BASE.booleanValue()))
            .andExpect(jsonPath("$.groupee").value(DEFAULT_GROUPEE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCompetence() throws Exception {
        // Get the competence
        restCompetenceMockMvc.perform(get("/api/competences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompetence() throws Exception {
        // Initialize the database
        competenceRepository.saveAndFlush(competence);

        int databaseSizeBeforeUpdate = competenceRepository.findAll().size();

        // Update the competence
        Competence updatedCompetence = competenceRepository.findById(competence.getId()).get();
        // Disconnect from session so that the updates on updatedCompetence are not directly saved in db
        em.detach(updatedCompetence);
        updatedCompetence
            .nom(UPDATED_NOM)
            .caracteristique(UPDATED_CARACTERISTIQUE)
            .deBase(UPDATED_DE_BASE)
            .groupee(UPDATED_GROUPEE);

        restCompetenceMockMvc.perform(put("/api/competences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompetence)))
            .andExpect(status().isOk());

        // Validate the Competence in the database
        List<Competence> competenceList = competenceRepository.findAll();
        assertThat(competenceList).hasSize(databaseSizeBeforeUpdate);
        Competence testCompetence = competenceList.get(competenceList.size() - 1);
        assertThat(testCompetence.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testCompetence.getCaracteristique()).isEqualTo(UPDATED_CARACTERISTIQUE);
        assertThat(testCompetence.isDeBase()).isEqualTo(UPDATED_DE_BASE);
        assertThat(testCompetence.isGroupee()).isEqualTo(UPDATED_GROUPEE);
    }

    @Test
    @Transactional
    public void updateNonExistingCompetence() throws Exception {
        int databaseSizeBeforeUpdate = competenceRepository.findAll().size();

        // Create the Competence

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetenceMockMvc.perform(put("/api/competences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(competence)))
            .andExpect(status().isBadRequest());

        // Validate the Competence in the database
        List<Competence> competenceList = competenceRepository.findAll();
        assertThat(competenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompetence() throws Exception {
        // Initialize the database
        competenceRepository.saveAndFlush(competence);

        int databaseSizeBeforeDelete = competenceRepository.findAll().size();

        // Delete the competence
        restCompetenceMockMvc.perform(delete("/api/competences/{id}", competence.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Competence> competenceList = competenceRepository.findAll();
        assertThat(competenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
