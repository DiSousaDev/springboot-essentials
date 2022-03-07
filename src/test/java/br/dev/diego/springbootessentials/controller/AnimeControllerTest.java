package br.dev.diego.springbootessentials.controller;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.entities.Anime;
import br.dev.diego.springbootessentials.service.AnimeService;
import br.dev.diego.springbootessentials.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static br.dev.diego.springbootessentials.AnimeMother.getAnime;
import static br.dev.diego.springbootessentials.AnimeMother.getAnimeList;
import static br.dev.diego.springbootessentials.AnimeMother.getAnimePaged;
import static br.dev.diego.springbootessentials.AnimeMother.getAnimePostRequestBody;
import static br.dev.diego.springbootessentials.AnimeMother.getAnimePutRequestBody;
import static br.dev.diego.springbootessentials.AnimeMother.getFormatLocalDateTimeToDatabaseStyle;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @Mock
    private DateUtil dateUtilMock;

    @BeforeEach
    void setUp() {
        when(animeServiceMock.listAll(any())).thenReturn(getAnimePaged());
        when(animeServiceMock.listAll()).thenReturn(getAnimeList());
        when(animeServiceMock.findByIdOrThrowBadRequestException(anyLong())).thenReturn(getAnime());
        when(animeServiceMock.findByName(anyString())).thenReturn(getAnimeList());
        when(animeServiceMock.save(any(AnimePostRequestBody.class))).thenReturn(getAnime());
        doNothing().when(animeServiceMock).update(any(AnimePutRequestBody.class));
        doNothing().when(animeServiceMock).delete(anyLong());
        when(dateUtilMock.formatLocalDateTimeToDatabaseStyle(any())).thenReturn(getFormatLocalDateTimeToDatabaseStyle());
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void listReturnListOfAnimesInsidePageObjectWhenSuccessful() {

        String expectedName = getAnime().getName();
        Page<Anime> animePaged = animeController.list(null).getBody();

        assertNotNull(animePaged);
        assertFalse(animePaged.toList().isEmpty());
        assertTrue(animePaged.toList().size() > 0);
        assertEquals(animePaged.toList().get(0).getName(), expectedName);

    }

    @Test
    @DisplayName("ListAll returns list of anime when successful")
    void listAllReturnListOfAnimesWhenSuccessful() {

        String expectedName = getAnime().getName();
        List<Anime> animeList = animeController.listAll().getBody();

        assertNotNull(animeList);
        assertFalse(animeList.isEmpty());
        assertEquals(animeList.get(0).getName(), expectedName);

    }

    @Test
    @DisplayName("Find By Id returns an anime when successful")
    void findByIdReturnAnimesWhenSuccessful() {

        Long expectedId = getAnime().getId();
        Anime anime = animeController.findById(1L).getBody();

        assertNotNull(anime);
        assertEquals(anime.getId(), expectedId);

    }

    @Test
    @DisplayName("Find By Name returns list of anime when successful")
    void findByNameReturnListOfAnimesWhenSuccessful() {

        String expectedName = getAnime().getName();
        List<Anime> animeList = animeController.findByName("Name").getBody();

        assertNotNull(animeList);
        assertFalse(animeList.isEmpty());
        assertEquals(animeList.get(0).getName(), expectedName);


    }

    @Test
    @DisplayName("Find By Name returns an empty list of anime when is not found")
    void findByNameReturnEmptyListOfAnimesWhenNotFound() {
        when(animeServiceMock.findByName(anyString())).thenReturn(Collections.emptyList());

        List<Anime> animeList = animeController.findByName("Name").getBody();

        assertNotNull(animeList);
        assertTrue(animeList.isEmpty());

    }

    @Test
    @DisplayName("Save returns an anime when successful")
    void saveReturnAnimesWhenSuccessful() {

        Anime anime = animeController.save(getAnimePostRequestBody()).getBody();

        assertNotNull(anime);
        assertEquals(anime, getAnime());

    }

    @Test
    @DisplayName("Update replace an anime when successful")
    void updateReplaceAnimesWhenSuccessful() {

        assertDoesNotThrow(() -> animeController.update(getAnimePutRequestBody()));

        ResponseEntity<Void> entity = animeController.update(getAnimePutRequestBody());

        assertNotNull(entity);
        assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);


    }

    @Test
    @DisplayName("Delete Remove an anime when successful")
    void deleteRemoveAnimesWhenSuccessful() {

        assertDoesNotThrow(() -> animeController.delete(1L));

        ResponseEntity<Void> entity = animeController.delete(1L);

        assertNotNull(entity);
        assertEquals(entity.getStatusCode(), HttpStatus.NO_CONTENT);


    }
}