package co.edu.unibague.agenda2.shared.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class IdTest {

    @Test
    void create_id_test(){
        var uuidRandom = UUID.randomUUID().toString();
        var id = new Id(uuidRandom);
        assertThat(id).isNotNull();
        assertThat(id.value().toString()).isEqualTo(uuidRandom);
        assertThat(id.value()).isEqualTo(UUID.fromString(uuidRandom));
    }
}