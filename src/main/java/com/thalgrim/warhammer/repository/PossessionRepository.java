package com.thalgrim.warhammer.repository;

import com.thalgrim.warhammer.domain.Possession;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Possession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PossessionRepository extends JpaRepository<Possession, Long> {
}
