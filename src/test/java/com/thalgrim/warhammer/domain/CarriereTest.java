package com.thalgrim.warhammer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.thalgrim.warhammer.web.rest.TestUtil;

public class CarriereTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Carriere.class);
        Carriere carriere1 = new Carriere();
        carriere1.setId(1L);
        Carriere carriere2 = new Carriere();
        carriere2.setId(carriere1.getId());
        assertThat(carriere1).isEqualTo(carriere2);
        carriere2.setId(2L);
        assertThat(carriere1).isNotEqualTo(carriere2);
        carriere1.setId(null);
        assertThat(carriere1).isNotEqualTo(carriere2);
    }
}
