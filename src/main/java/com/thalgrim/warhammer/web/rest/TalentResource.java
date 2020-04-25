package com.thalgrim.warhammer.web.rest;

import com.thalgrim.warhammer.domain.Talent;
import com.thalgrim.warhammer.repository.TalentRepository;
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

/**
 * REST controller for managing {@link com.thalgrim.warhammer.domain.Talent}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TalentResource {

    private final Logger log = LoggerFactory.getLogger(TalentResource.class);

    private static final String ENTITY_NAME = "warhammerJhipsterTalent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TalentRepository talentRepository;

    public TalentResource(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    /**
     * {@code POST  /talents} : Create a new talent.
     *
     * @param talent the talent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new talent, or with status {@code 400 (Bad Request)} if the talent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/talents")
    public ResponseEntity<Talent> createTalent(@RequestBody Talent talent) throws URISyntaxException {
        log.debug("REST request to save Talent : {}", talent);
        if (talent.getId() != null) {
            throw new BadRequestAlertException("A new talent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Talent result = talentRepository.save(talent);
        return ResponseEntity.created(new URI("/api/talents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /talents} : Updates an existing talent.
     *
     * @param talent the talent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated talent,
     * or with status {@code 400 (Bad Request)} if the talent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the talent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/talents")
    public ResponseEntity<Talent> updateTalent(@RequestBody Talent talent) throws URISyntaxException {
        log.debug("REST request to update Talent : {}", talent);
        if (talent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Talent result = talentRepository.save(talent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, talent.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /talents} : get all the talents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of talents in body.
     */
    @GetMapping("/talents")
    public List<Talent> getAllTalents() {
        log.debug("REST request to get all Talents");
        return talentRepository.findAll();
    }

    /**
     * {@code GET  /talents/:id} : get the "id" talent.
     *
     * @param id the id of the talent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the talent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/talents/{id}")
    public ResponseEntity<Talent> getTalent(@PathVariable Long id) {
        log.debug("REST request to get Talent : {}", id);
        Optional<Talent> talent = talentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(talent);
    }

    /**
     * {@code DELETE  /talents/:id} : delete the "id" talent.
     *
     * @param id the id of the talent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/talents/{id}")
    public ResponseEntity<Void> deleteTalent(@PathVariable Long id) {
        log.debug("REST request to delete Talent : {}", id);
        talentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
