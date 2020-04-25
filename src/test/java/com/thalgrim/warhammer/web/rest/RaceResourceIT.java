package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.WarhammerJhipsterApp;
import com.thalgrim.warhammer.domain.Race;
import com.thalgrim.warhammer.repository.RaceRepository;

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
 * Integration tests for the {@link RaceResource} REST controller.
 */
@SpringBootTest(classes = WarhammerJhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RaceResourceIT {

    private static final Integer DEFAULT_CC = 1;
    private static final Integer UPDATED_CC = 2;

    private static final Integer DEFAULT_CT = 1;
    private static final Integer UPDATED_CT = 2;

    private static final Integer DEFAULT_FO = 1;
    private static final Integer UPDATED_FO = 2;

    private static final Integer DEFAULT_EN = 1;
    private static final Integer UPDATED_EN = 2;

    private static final Integer DEFAULT_INI = 1;
    private static final Integer UPDATED_INI = 2;

    private static final Integer DEFAULT_AG = 1;
    private static final Integer UPDATED_AG = 2;

    private static final Integer DEFAULT_DEX = 1;
    private static final Integer UPDATED_DEX = 2;

    private static final Integer DEFAULT_INTE = 1;
    private static final Integer UPDATED_INTE = 2;

    private static final Integer DEFAULT_FM = 1;
    private static final Integer UPDATED_FM = 2;

    private static final Integer DEFAULT_SOC = 1;
    private static final Integer UPDATED_SOC = 2;

    private static final Integer DEFAULT_DESTIN = 1;
    private static final Integer UPDATED_DESTIN = 2;

    private static final Integer DEFAULT_RESILIENCE = 1;
    private static final Integer UPDATED_RESILIENCE = 2;

    private static final Integer DEFAULT_EXPERIENCE = 1;
    private static final Integer UPDATED_EXPERIENCE = 2;

    private static final Integer DEFAULT_MOUVEMENT = 1;
    private static final Integer UPDATED_MOUVEMENT = 2;

    private static final Integer DEFAULT_POINTS_SUPLEMENTAIRES = 1;
    private static final Integer UPDATED_POINTS_SUPLEMENTAIRES = 2;

    private static final Integer DEFAULT_BLESSURES = 1;
    private static final Integer UPDATED_BLESSURES = 2;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRaceMockMvc;

    private Race race;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Race createEntity(EntityManager em) {
        Race race = new Race()
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
            .destin(DEFAULT_DESTIN)
            .resilience(DEFAULT_RESILIENCE)
            .experience(DEFAULT_EXPERIENCE)
            .mouvement(DEFAULT_MOUVEMENT)
            .pointsSuplementaires(DEFAULT_POINTS_SUPLEMENTAIRES)
            .blessures(DEFAULT_BLESSURES)
            .nom(DEFAULT_NOM);
        return race;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Race createUpdatedEntity(EntityManager em) {
        Race race = new Race()
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
            .destin(UPDATED_DESTIN)
            .resilience(UPDATED_RESILIENCE)
            .experience(UPDATED_EXPERIENCE)
            .mouvement(UPDATED_MOUVEMENT)
            .pointsSuplementaires(UPDATED_POINTS_SUPLEMENTAIRES)
            .blessures(UPDATED_BLESSURES)
            .nom(UPDATED_NOM);
        return race;
    }

    @BeforeEach
    public void initTest() {
        race = createEntity(em);
    }

    @Test
    @Transactional
    public void createRace() throws Exception {
        int databaseSizeBeforeCreate = raceRepository.findAll().size();

        // Create the Race
        restRaceMockMvc.perform(post("/api/races")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(race)))
            .andExpect(status().isCreated());

        // Validate the Race in the database
        List<Race> raceList = raceRepository.findAll();
        assertThat(raceList).hasSize(databaseSizeBeforeCreate + 1);
        Race testRace = raceList.get(raceList.size() - 1);
        assertThat(testRace.getCc()).isEqualTo(DEFAULT_CC);
        assertThat(testRace.getCt()).isEqualTo(DEFAULT_CT);
        assertThat(testRace.getFo()).isEqualTo(DEFAULT_FO);
        assertThat(testRace.getEn()).isEqualTo(DEFAULT_EN);
        assertThat(testRace.getIni()).isEqualTo(DEFAULT_INI);
        assertThat(testRace.getAg()).isEqualTo(DEFAULT_AG);
        assertThat(testRace.getDex()).isEqualTo(DEFAULT_DEX);
        assertThat(testRace.getInte()).isEqualTo(DEFAULT_INTE);
        assertThat(testRace.getFm()).isEqualTo(DEFAULT_FM);
        assertThat(testRace.getSoc()).isEqualTo(DEFAULT_SOC);
        assertThat(testRace.getDestin()).isEqualTo(DEFAULT_DESTIN);
        assertThat(testRace.getResilience()).isEqualTo(DEFAULT_RESILIENCE);
        assertThat(testRace.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testRace.getMouvement()).isEqualTo(DEFAULT_MOUVEMENT);
        assertThat(testRace.getPointsSuplementaires()).isEqualTo(DEFAULT_POINTS_SUPLEMENTAIRES);
        assertThat(testRace.getBlessures()).isEqualTo(DEFAULT_BLESSURES);
        assertThat(testRace.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    public void createRaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = raceRepository.findAll().size();

        // Create the Race with an existing ID
        race.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRaceMockMvc.perform(post("/api/races")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(race)))
            .andExpect(status().isBadRequest());

        // Validate the Race in the database
        List<Race> raceList = raceRepository.findAll();
        assertThat(raceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRaces() throws Exception {
        // Initialize the database
        raceRepository.saveAndFlush(race);

        // Get all the raceList
        restRaceMockMvc.perform(get("/api/races?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(race.getId().intValue())))
            .andExpect(jsonPath("$.[*].cc").value(hasItem(DEFAULT_CC)))
            .andExpect(jsonPath("$.[*].ct").value(hasItem(DEFAULT_CT)))
            .andExpect(jsonPath("$.[*].fo").value(hasItem(DEFAULT_FO)))
            .andExpect(jsonPath("$.[*].en").value(hasItem(DEFAULT_EN)))
            .andExpect(jsonPath("$.[*].ini").value(hasItem(DEFAULT_INI)))
            .andExpect(jsonPath("$.[*].ag").value(hasItem(DEFAULT_AG)))
            .andExpect(jsonPath("$.[*].dex").value(hasItem(DEFAULT_DEX)))
            .andExpect(jsonPath("$.[*].inte").value(hasItem(DEFAULT_INTE)))
            .andExpect(jsonPath("$.[*].fm").value(hasItem(DEFAULT_FM)))
            .andExpect(jsonPath("$.[*].soc").value(hasItem(DEFAULT_SOC)))
            .andExpect(jsonPath("$.[*].destin").value(hasItem(DEFAULT_DESTIN)))
            .andExpect(jsonPath("$.[*].resilience").value(hasItem(DEFAULT_RESILIENCE)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE)))
            .andExpect(jsonPath("$.[*].mouvement").value(hasItem(DEFAULT_MOUVEMENT)))
            .andExpect(jsonPath("$.[*].pointsSuplementaires").value(hasItem(DEFAULT_POINTS_SUPLEMENTAIRES)))
            .andExpect(jsonPath("$.[*].blessures").value(hasItem(DEFAULT_BLESSURES)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)));
    }
    
    @Test
    @Transactional
    public void getRace() throws Exception {
        // Initialize the database
        raceRepository.saveAndFlush(race);

        // Get the race
        restRaceMockMvc.perform(get("/api/races/{id}", race.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(race.getId().intValue()))
            .andExpect(jsonPath("$.cc").value(DEFAULT_CC))
            .andExpect(jsonPath("$.ct").value(DEFAULT_CT))
            .andExpect(jsonPath("$.fo").value(DEFAULT_FO))
            .andExpect(jsonPath("$.en").value(DEFAULT_EN))
            .andExpect(jsonPath("$.ini").value(DEFAULT_INI))
            .andExpect(jsonPath("$.ag").value(DEFAULT_AG))
            .andExpect(jsonPath("$.dex").value(DEFAULT_DEX))
            .andExpect(jsonPath("$.inte").value(DEFAULT_INTE))
            .andExpect(jsonPath("$.fm").value(DEFAULT_FM))
            .andExpect(jsonPath("$.soc").value(DEFAULT_SOC))
            .andExpect(jsonPath("$.destin").value(DEFAULT_DESTIN))
            .andExpect(jsonPath("$.resilience").value(DEFAULT_RESILIENCE))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE))
            .andExpect(jsonPath("$.mouvement").value(DEFAULT_MOUVEMENT))
            .andExpect(jsonPath("$.pointsSuplementaires").value(DEFAULT_POINTS_SUPLEMENTAIRES))
            .andExpect(jsonPath("$.blessures").value(DEFAULT_BLESSURES))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM));
    }

    @Test
    @Transactional
    public void getNonExistingRace() throws Exception {
        // Get the race
        restRaceMockMvc.perform(get("/api/races/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRace() throws Exception {
        // Initialize the database
        raceRepository.saveAndFlush(race);

        int databaseSizeBeforeUpdate = raceRepository.findAll().size();

        // Update the race
        Race updatedRace = raceRepository.findById(race.getId()).get();
        // Disconnect from session so that the updates on updatedRace are not directly saved in db
        em.detach(updatedRace);
        updatedRace
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
            .destin(UPDATED_DESTIN)
            .resilience(UPDATED_RESILIENCE)
            .experience(UPDATED_EXPERIENCE)
            .mouvement(UPDATED_MOUVEMENT)
            .pointsSuplementaires(UPDATED_POINTS_SUPLEMENTAIRES)
            .blessures(UPDATED_BLESSURES)
            .nom(UPDATED_NOM);

        restRaceMockMvc.perform(put("/api/races")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRace)))
            .andExpect(status().isOk());

        // Validate the Race in the database
        List<Race> raceList = raceRepository.findAll();
        assertThat(raceList).hasSize(databaseSizeBeforeUpdate);
        Race testRace = raceList.get(raceList.size() - 1);
        assertThat(testRace.getCc()).isEqualTo(UPDATED_CC);
        assertThat(testRace.getCt()).isEqualTo(UPDATED_CT);
        assertThat(testRace.getFo()).isEqualTo(UPDATED_FO);
        assertThat(testRace.getEn()).isEqualTo(UPDATED_EN);
        assertThat(testRace.getIni()).isEqualTo(UPDATED_INI);
        assertThat(testRace.getAg()).isEqualTo(UPDATED_AG);
        assertThat(testRace.getDex()).isEqualTo(UPDATED_DEX);
        assertThat(testRace.getInte()).isEqualTo(UPDATED_INTE);
        assertThat(testRace.getFm()).isEqualTo(UPDATED_FM);
        assertThat(testRace.getSoc()).isEqualTo(UPDATED_SOC);
        assertThat(testRace.getDestin()).isEqualTo(UPDATED_DESTIN);
        assertThat(testRace.getResilience()).isEqualTo(UPDATED_RESILIENCE);
        assertThat(testRace.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testRace.getMouvement()).isEqualTo(UPDATED_MOUVEMENT);
        assertThat(testRace.getPointsSuplementaires()).isEqualTo(UPDATED_POINTS_SUPLEMENTAIRES);
        assertThat(testRace.getBlessures()).isEqualTo(UPDATED_BLESSURES);
        assertThat(testRace.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    public void updateNonExistingRace() throws Exception {
        int databaseSizeBeforeUpdate = raceRepository.findAll().size();

        // Create the Race

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRaceMockMvc.perform(put("/api/races")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(race)))
            .andExpect(status().isBadRequest());

        // Validate the Race in the database
        List<Race> raceList = raceRepository.findAll();
        assertThat(raceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRace() throws Exception {
        // Initialize the database
        raceRepository.saveAndFlush(race);

        int databaseSizeBeforeDelete = raceRepository.findAll().size();

        // Delete the race
        restRaceMockMvc.perform(delete("/api/races/{id}", race.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Race> raceList = raceRepository.findAll();
        assertThat(raceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
