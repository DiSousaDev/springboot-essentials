package br.dev.diego.springbootessentials.repository;

import br.dev.diego.springbootessentials.AnimeMother;
import br.dev.diego.springbootessentials.entities.Anime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for AnimeRepository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when successful")
    void savePersistAnimeWhenSuccessful() {
        Anime animeToBeSaved = AnimeMother.getAnime();
        Anime savedAnime = animeRepository.save(animeToBeSaved);
        assertNotNull(savedAnime);
        assertEquals(savedAnime.getName(), animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save update anime when successful")
    void saveUpdateAnimeWhenSuccessful() {
        Anime animeToBeSaved = AnimeMother.getAnime();
        Anime animeToBeUpdated = animeRepository.save(animeToBeSaved);
        animeToBeUpdated.setName("Hajime no Ippo Updated");

        Anime animeUpdated = animeRepository.save(animeToBeUpdated);
        assertNotNull(animeUpdated);
        assertEquals(animeUpdated.getName(), animeToBeUpdated.getName());
    }

    @Test
    @DisplayName("Delete remove anime when successful")
    void deleteRemoveAnimeWhenSuccessful() {
        Anime animeToBeSaved = AnimeMother.getAnime();
        Anime animeToBeDeleted = animeRepository.save(animeToBeSaved);

        animeRepository.delete(animeToBeDeleted);

        Optional<Anime> obj = animeRepository.findById(animeToBeDeleted.getId());

        assertTrue(obj.isEmpty());

    }

    @Test
    @DisplayName("Find by name returns list of anime when successful")
    void findByNameReturnListOfAnimeWhenSuccessful() {
        Anime animeToBeListed1 = AnimeMother.getAnime();
        Anime animeToBeListed2 = AnimeMother.getAnime();

        animeRepository.save(animeToBeListed1);
        animeRepository.save(animeToBeListed2);

        List<Anime> animeList = animeRepository.findByName(animeToBeListed1.getName());

        assertFalse(animeList.isEmpty());
        assertEquals(2, animeList.size());
        assertTrue(animeList.contains(animeToBeListed1));
        assertTrue(animeList.contains(animeToBeListed2));

    }

    @Test
    @DisplayName("Find by name returns empty list when no anime is found")
    void findByNameReturnEmptyListWhenAnimeIsNotFound() {

        List<Anime> animeList = animeRepository.findByName("Unkwnon");
        assertTrue(animeList.isEmpty());

    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void saveThrowConstraintViolationExceptionWhenNameIsEmpty() {

        Anime animeToBeSaved = AnimeMother.getAnime();
        animeToBeSaved.setName("");

        ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class,
                () -> animeRepository.save(animeToBeSaved));

        assertTrue(constraintViolationException.getMessage().contains("The name cannot be empty"));

    }
}