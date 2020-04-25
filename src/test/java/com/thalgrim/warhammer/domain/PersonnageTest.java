package com.thalgrim.warhammer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.thalgrim.warhammer.web.rest.TestUtil;

public class PersonnageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personnage.class);
        Personnage personnage1 = new Personnage();
        personnage1.setId(1L);
        Personnage personnage2 = new Personnage();
        personnage2.setId(personnage1.getId());
        assertThat(personnage1).isEqualTo(personnage2);
        personnage2.setId(2L);
        assertThat(personnage1).isNotEqualTo(personnage2);
        personnage1.setId(null);
        assertThat(personnage1).isNotEqualTo(personnage2);
    }
}
