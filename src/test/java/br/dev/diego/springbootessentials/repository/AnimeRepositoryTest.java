package br.dev.diego.springbootessentials.repository;

import br.dev.diego.springbootessentials.AnimeMother;
import br.dev.diego.springbootessentials.entities.Anime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AnimeRepository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when successful")
    void savePersistAnimeWhenSuccessful() {
        Anime animeToBeSaved = AnimeMother.getAnime();
        Anime savedAnime = animeRepository.save(animeToBeSaved);
        assertNotNull(savedAnime);
        assertEquals(savedAnime.getName(), animeToBeSaved.getName());
    }
}