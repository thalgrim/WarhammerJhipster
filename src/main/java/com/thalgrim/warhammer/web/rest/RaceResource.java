package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Race;
import com.thalgrim.warhammer.repository.RaceRepository;
import com.thalgrim.warhammer.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Race}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RaceResource {

    private final Logger log = LoggerFactory.getLogger(RaceResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterRace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RaceRepository raceRepository;

    public RaceResource(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    /**
     * {@code POST  /races} : Create a new race.
     *
     * @param race the race to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new race, or with status {@code 400 (Bad Request)} if the race has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/races")
    public ResponseEntity<Race> createRace(@RequestBody Race race) throws URISyntaxException {
        log.debug("REST request to save Race : {}", race);
        if (race.getId() != null) {
            throw new BadRequestAlertException("A new race cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Race result = raceRepository.save(race);
        return ResponseEntity.created(new URI("/api/races/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /races} : Updates an existing race.
     *
     * @param race the race to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated race,
     * or with status {@code 400 (Bad Request)} if the race is not valid,
     * or with status {@code 500 (Internal Server Error)} if the race couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/races")
    public ResponseEntity<Race> updateRace(@RequestBody Race race) throws URISyntaxException {
        log.debug("REST request to update Race : {}", race);
        if (race.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Race result = raceRepository.save(race);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, race.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /races} : get all the races.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of races in body.
     */
    @GetMapping("/races")
    public List<Race> getAllRaces(@RequestParam(required = false) String filter) {
        if ("personnage-is-null".equals(filter)) {
            log.debug("REST request to get all Races where personnage is null");
            return StreamSupport
                .stream(raceRepository.findAll().spliterator(), false)
                .filter(race -> race.getPersonnage() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Races");
        return raceRepository.findAll();
    }

    /**
     * {@code GET  /races/:id} : get the "id" race.
     *
     * @param id the id of the race to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the race, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/races/{id}")
    public ResponseEntity<Race> getRace(@PathVariable Long id) {
        log.debug("REST request to get Race : {}", id);
        Optional<Race> race = raceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(race);
    }

    /**
     * {@code DELETE  /races/:id} : delete the "id" race.
     *
     * @param id the id of the race to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/races/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        log.debug("REST request to delete Race : {}", id);
        raceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
