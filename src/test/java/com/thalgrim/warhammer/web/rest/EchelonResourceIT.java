package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.WarhammerJhipsterApp;
import com.thalgrim.warhammer.domain.Echelon;
import com.thalgrim.warhammer.repository.EchelonRepository;

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
 * Integration tests for the {@link EchelonResource} REST controller.
 */
@SpringBootTest(classes = WarhammerJhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EchelonResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_NIVEAU = 1;
    private static final Integer UPDATED_NIVEAU = 2;

    private static final Boolean DEFAULT_CC = false;
    private static final Boolean UPDATED_CC = true;

    private static final Boolean DEFAULT_CT = false;
    private static final Boolean UPDATED_CT = true;

    private static final Boolean DEFAULT_FO = false;
    private static final Boolean UPDATED_FO = true;

    private static final Boolean DEFAULT_EN = false;
    private static final Boolean UPDATED_EN = true;

    private static final Boolean DEFAULT_INI = false;
    private static final Boolean UPDATED_INI = true;

    private static final Boolean DEFAULT_AG = false;
    private static final Boolean UPDATED_AG = true;

    private static final Boolean DEFAULT_DEX = false;
    private static final Boolean UPDATED_DEX = true;

    private static final Boolean DEFAULT_INTE = false;
    private static final Boolean UPDATED_INTE = true;

    private static final Boolean DEFAULT_FM = false;
    private static final Boolean UPDATED_FM = true;

    private static final Boolean DEFAULT_SOC = false;
    private static final Boolean UPDATED_SOC = true;

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    @Autowired
    private EchelonRepository echelonRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEchelonMockMvc;

    private Echelon echelon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Echelon createEntity(EntityManager em) {
        Echelon echelon = new Echelon()
            .nom(DEFAULT_NOM)
            .niveau(DEFAULT_NIVEAU)
            .cc(DEFAULT_CC)
            .ct(DEFAULT_CT)
            .fo(DEFAULT_FO)
            .en(DEFAULT_EN)
            .ini(DEFAULT_INI)
            .ag(DEFAULT_AG)
            .dex(DEFAULT_DEX)
            .inte(DEFAULT_INTE)
            .fm(DEFAULT_FM)
            .soc(DEFAULT_SOC)
            .statut(DEFAULT_STATUT);
        return echelon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Echelon createUpdatedEntity(EntityManager em) {
        Echelon echelon = new Echelon()
            .nom(UPDATED_NOM)
            .niveau(UPDATED_NIVEAU)
            .cc(UPDATED_CC)
            .ct(UPDATED_CT)
            .fo(UPDATED_FO)
            .en(UPDATED_EN)
            .ini(UPDATED_INI)
            .ag(UPDATED_AG)
            .dex(UPDATED_DEX)
            .inte(UPDATED_INTE)
            .fm(UPDATED_FM)
            .soc(UPDATED_SOC)
            .statut(UPDATED_STATUT);
        return echelon;
    }

    @BeforeEach
    public void initTest() {
        echelon = createEntity(em);
    }

    @Test
    @Transactional
    public void createEchelon() throws Exception {
        int databaseSizeBeforeCreate = echelonRepository.findAll().size();

        // Create the Echelon
        restEchelonMockMvc.perform(post("/api/echelons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(echelon)))
            .andExpect(status().isCreated());

        // Validate the Echelon in the database
        List<Echelon> echelonList = echelonRepository.findAll();
        assertThat(echelonList).hasSize(databaseSizeBeforeCreate + 1);
        Echelon testEchelon = echelonList.get(echelonList.size() - 1);
        assertThat(testEchelon.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEchelon.getNiveau()).isEqualTo(DEFAULT_NIVEAU);
        assertThat(testEchelon.isCc()).isEqualTo(DEFAULT_CC);
        assertThat(testEchelon.isCt()).isEqualTo(DEFAULT_CT);
        assertThat(testEchelon.isFo()).isEqualTo(DEFAULT_FO);
        assertThat(testEchelon.isEn()).isEqualTo(DEFAULT_EN);
        assertThat(testEchelon.isIni()).isEqualTo(DEFAULT_INI);
        assertThat(testEchelon.isAg()).isEqualTo(DEFAULT_AG);
        assertThat(testEchelon.isDex()).isEqualTo(DEFAULT_DEX);
        assertThat(testEchelon.isInte()).isEqualTo(DEFAULT_INTE);
        assertThat(testEchelon.isFm()).isEqualTo(DEFAULT_FM);
        assertThat(testEchelon.isSoc()).isEqualTo(DEFAULT_SOC);
        assertThat(testEchelon.getStatut()).isEqualTo(DEFAULT_STATUT);
    }

    @Test
    @Transactional
    public void createEchelonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = echelonRepository.findAll().size();

        // Create the Echelon with an existing ID
        echelon.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEchelonMockMvc.perform(post("/api/echelons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(echelon)))
            .andExpect(status().isBadRequest());

        // Validate the Echelon in the database
        List<Echelon> echelonList = echelonRepository.findAll();
        assertThat(echelonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEchelons() throws Exception {
        // Initialize the database
        echelonRepository.saveAndFlush(echelon);

        // Get all the echelonList
        restEchelonMockMvc.perform(get("/api/echelons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(echelon.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].niveau").value(hasItem(DEFAULT_NIVEAU)))
            .andExpect(jsonPath("$.[*].cc").value(hasItem(DEFAULT_CC.booleanValue())))
            .andExpect(jsonPath("$.[*].ct").value(hasItem(DEFAULT_CT.booleanValue())))
            .andExpect(jsonPath("$.[*].fo").value(hasItem(DEFAULT_FO.booleanValue())))
            .andExpect(jsonPath("$.[*].en").value(hasItem(DEFAULT_EN.booleanValue())))
            .andExpect(jsonPath("$.[*].ini").value(hasItem(DEFAULT_INI.booleanValue())))
            .andExpect(jsonPath("$.[*].ag").value(hasItem(DEFAULT_AG.booleanValue())))
            .andExpect(jsonPath("$.[*].dex").value(hasItem(DEFAULT_DEX.booleanValue())))
            .andExpect(jsonPath("$.[*].inte").value(hasItem(DEFAULT_INTE.booleanValue())))
            .andExpect(jsonPath("$.[*].fm").value(hasItem(DEFAULT_FM.booleanValue())))
            .andExpect(jsonPath("$.[*].soc").value(hasItem(DEFAULT_SOC.booleanValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)));
    }
    
    @Test
    @Transactional
    public void getEchelon() throws Exception {
        // Initialize the database
        echelonRepository.saveAndFlush(echelon);

        // Get the echelon
        restEchelonMockMvc.perform(get("/api/echelons/{id}", echelon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(echelon.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.niveau").value(DEFAULT_NIVEAU))
            .andExpect(jsonPath("$.cc").value(DEFAULT_CC.booleanValue()))
            .andExpect(jsonPath("$.ct").value(DEFAULT_CT.booleanValue()))
            .andExpect(jsonPath("$.fo").value(DEFAULT_FO.booleanValue()))
            .andExpect(jsonPath("$.en").value(DEFAULT_EN.booleanValue()))
            .andExpect(jsonPath("$.ini").value(DEFAULT_INI.booleanValue()))
            .andExpect(jsonPath("$.ag").value(DEFAULT_AG.booleanValue()))
            .andExpect(jsonPath("$.dex").value(DEFAULT_DEX.booleanValue()))
            .andExpect(jsonPath("$.inte").value(DEFAULT_INTE.booleanValue()))
            .andExpect(jsonPath("$.fm").value(DEFAULT_FM.booleanValue()))
            .andExpect(jsonPath("$.soc").value(DEFAULT_SOC.booleanValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT));
    }

    @Test
    @Transactional
    public void getNonExistingEchelon() throws Exception {
        // Get the echelon
        restEchelonMockMvc.perform(get("/api/echelons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEchelon() throws Exception {
        // Initialize the database
        echelonRepository.saveAndFlush(echelon);

        int databaseSizeBeforeUpdate = echelonRepository.findAll().size();

        // Update the echelon
        Echelon updatedEchelon = echelonRepository.findById(echelon.getId()).get();
        // Disconnect from session so that the updates on updatedEchelon are not directly saved in db
        em.detach(updatedEchelon);
        updatedEchelon
            .nom(UPDATED_NOM)
            .niveau(UPDATED_NIVEAU)
            .cc(UPDATED_CC)
            .ct(UPDATED_CT)
            .fo(UPDATED_FO)
            .en(UPDATED_EN)
            .ini(UPDATED_INI)
            .ag(UPDATED_AG)
            .dex(UPDATED_DEX)
            .inte(UPDATED_INTE)
            .fm(UPDATED_FM)
            .soc(UPDATED_SOC)
            .statut(UPDATED_STATUT);

        restEchelonMockMvc.perform(put("/api/echelons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEchelon)))
            .andExpect(status().isOk());

        // Validate the Echelon in the database
        List<Echelon> echelonList = echelonRepository.findAll();
        assertThat(echelonList).hasSize(databaseSizeBeforeUpdate);
        Echelon testEchelon = echelonList.get(echelonList.size() - 1);
        assertThat(testEchelon.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEchelon.getNiveau()).isEqualTo(UPDATED_NIVEAU);
        assertThat(testEchelon.isCc()).isEqualTo(UPDATED_CC);
        assertThat(testEchelon.isCt()).isEqualTo(UPDATED_CT);
        assertThat(testEchelon.isFo()).isEqualTo(UPDATED_FO);
        assertThat(testEchelon.isEn()).isEqualTo(UPDATED_EN);
        assertThat(testEchelon.isIni()).isEqualTo(UPDATED_INI);
        assertThat(testEchelon.isAg()).isEqualTo(UPDATED_AG);
        assertThat(testEchelon.isDex()).isEqualTo(UPDATED_DEX);
        assertThat(testEchelon.isInte()).isEqualTo(UPDATED_INTE);
        assertThat(testEchelon.isFm()).isEqualTo(UPDATED_FM);
        assertThat(testEchelon.isSoc()).isEqualTo(UPDATED_SOC);
        assertThat(testEchelon.getStatut()).isEqualTo(UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingEchelon() throws Exception {
        int databaseSizeBeforeUpdate = echelonRepository.findAll().size();

        // Create the Echelon

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEchelonMockMvc.perform(put("/api/echelons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(echelon)))
            .andExpect(status().isBadRequest());

        // Validate the Echelon in the database
        List<Echelon> echelonList = echelonRepository.findAll();
        assertThat(echelonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEchelon() throws Exception {
        // Initialize the database
        echelonRepository.saveAndFlush(echelon);

        int databaseSizeBeforeDelete = echelonRepository.findAll().size();

        // Delete the echelon
        restEchelonMockMvc.perform(delete("/api/echelons/{id}", echelon.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Echelon> echelonList = echelonRepository.findAll();
        assertThat(echelonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
