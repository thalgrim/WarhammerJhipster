package com.thalgrim.warhammer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.thalgrim.warhammer.web.rest.TestUtil;

public class EchelonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Echelon.class);
        Echelon echelon1 = new Echelon();
        echelon1.setId(1L);
        Echelon echelon2 = new Echelon();
        echelon2.setId(echelon1.getId());
        assertThat(echelon1).isEqualTo(echelon2);
        echelon2.setId(2L);
        assertThat(echelon1).isNotEqualTo(echelon2);
        echelon1.setId(null);
        assertThat(echelon1).isNotEqualTo(echelon2);
    }
}
