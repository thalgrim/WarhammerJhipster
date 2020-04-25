package com.thalgrim.warhammer.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.thalgrim.warhammer.web.rest.TestUtil;

public class PossessionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Possession.class);
        Possession possession1 = new Possession();
        possession1.setId(1L);
        Possession possession2 = new Possession();
        possession2.setId(possession1.getId());
        assertThat(possession1).isEqualTo(possession2);
        possession2.setId(2L);
        assertThat(possession1).isNotEqualTo(possession2);
        possession1.setId(null);
        assertThat(possession1).isNotEqualTo(possession2);
    }
}
