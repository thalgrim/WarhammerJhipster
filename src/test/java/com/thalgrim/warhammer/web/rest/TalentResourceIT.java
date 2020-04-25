package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.WarhammerJhipsterApp;
import com.thalgrim.warhammer.domain.Talent;
import com.thalgrim.warhammer.repository.TalentRepository;

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
 * Integration tests for the {@link TalentResource} REST controller.
 */
@SpringBootTest(classes = WarhammerJhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TalentResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAXI = 1;
    private static final Integer UPDATED_MAXI = 2;

    private static final String DEFAULT_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TEST = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTalentMockMvc;

    private Talent talent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Talent createEntity(EntityManager em) {
        Talent talent = new Talent()
            .nom(DEFAULT_NOM)
            .maxi(DEFAULT_MAXI)
            .test(DEFAULT_TEST)
            .description(DEFAULT_DESCRIPTION);
        return talent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Talent createUpdatedEntity(EntityManager em) {
        Talent talent = new Talent()
            .nom(UPDATED_NOM)
            .maxi(UPDATED_MAXI)
            .test(UPDATED_TEST)
            .description(UPDATED_DESCRIPTION);
        return talent;
    }

    @BeforeEach
    public void initTest() {
        talent = createEntity(em);
    }

    @Test
    @Transactional
    public void createTalent() throws Exception {
        int databaseSizeBeforeCreate = talentRepository.findAll().size();

        // Create the Talent
        restTalentMockMvc.perform(post("/api/talents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(talent)))
            .andExpect(status().isCreated());

        // Validate the Talent in the database
        List<Talent> talentList = talentRepository.findAll();
        assertThat(talentList).hasSize(databaseSizeBeforeCreate + 1);
        Talent testTalent = talentList.get(talentList.size() - 1);
        assertThat(testTalent.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTalent.getMaxi()).isEqualTo(DEFAULT_MAXI);
        assertThat(testTalent.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testTalent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTalentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = talentRepository.findAll().size();

        // Create the Talent with an existing ID
        talent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTalentMockMvc.perform(post("/api/talents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(talent)))
            .andExpect(status().isBadRequest());

        // Validate the Talent in the database
        List<Talent> talentList = talentRepository.findAll();
        assertThat(talentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTalents() throws Exception {
        // Initialize the database
        talentRepository.saveAndFlush(talent);

        // Get all the talentList
        restTalentMockMvc.perform(get("/api/talents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(talent.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].maxi").value(hasItem(DEFAULT_MAXI)))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getTalent() throws Exception {
        // Initialize the database
        talentRepository.saveAndFlush(talent);

        // Get the talent
        restTalentMockMvc.perform(get("/api/talents/{id}", talent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(talent.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.maxi").value(DEFAULT_MAXI))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingTalent() throws Exception {
        // Get the talent
        restTalentMockMvc.perform(get("/api/talents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTalent() throws Exception {
        // Initialize the database
        talentRepository.saveAndFlush(talent);

        int databaseSizeBeforeUpdate = talentRepository.findAll().size();

        // Update the talent
        Talent updatedTalent = talentRepository.findById(talent.getId()).get();
        // Disconnect from session so that the updates on updatedTalent are not directly saved in db
        em.detach(updatedTalent);
        updatedTalent
            .nom(UPDATED_NOM)
            .maxi(UPDATED_MAXI)
            .test(UPDATED_TEST)
            .description(UPDATED_DESCRIPTION);

        restTalentMockMvc.perform(put("/api/talents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTalent)))
            .andExpect(status().isOk());

        // Validate the Talent in the database
        List<Talent> talentList = talentRepository.findAll();
        assertThat(talentList).hasSize(databaseSizeBeforeUpdate);
        Talent testTalent = talentList.get(talentList.size() - 1);
        assertThat(testTalent.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTalent.getMaxi()).isEqualTo(UPDATED_MAXI);
        assertThat(testTalent.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testTalent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTalent() throws Exception {
        int databaseSizeBeforeUpdate = talentRepository.findAll().size();

        // Create the Talent

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTalentMockMvc.perform(put("/api/talents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(talent)))
            .andExpect(status().isBadRequest());

        // Validate the Talent in the database
        List<Talent> talentList = talentRepository.findAll();
        assertThat(talentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTalent() throws Exception {
        // Initialize the database
        talentRepository.saveAndFlush(talent);

        int databaseSizeBeforeDelete = talentRepository.findAll().size();

        // Delete the talent
        restTalentMockMvc.perform(delete("/api/talents/{id}", talent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Talent> talentList = talentRepository.findAll();
        assertThat(talentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
