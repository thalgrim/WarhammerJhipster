package com.thalgrim.warhammer.repository;

import com.thalgrim.warhammer.domain.Competence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Competence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
