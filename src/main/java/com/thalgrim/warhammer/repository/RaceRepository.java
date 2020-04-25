package com.thalgrim.warhammer.repository;

import com.thalgrim.warhammer.domain.Race;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Race entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
}
