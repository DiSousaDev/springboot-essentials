package br.dev.diego.springbootessentials;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.entities.Anime;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AnimeMother {

    public static Anime getAnime() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setName("Hajime no Ippo");
        return anime;
    }

    public static Anime getNewAnime() {
        Anime anime = new Anime();
        anime.setName("Hajime no Ippo");
        return anime;
    }

    public static Anime getAnimeUpdated() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setName("Hajime no Ippo Updated");
        return anime;
    }

    public static PageImpl<Anime> getAnimePaged() {
        return new PageImpl<>(List.of(AnimeMother.getAnime()));
    }

    public static List<Anime> getAnimeList() {
        return List.of(AnimeMother.getAnime());
    }

    public static String getFormatLocalDateTimeToDatabaseStyle() {
        return "2022-01-30 12:54:13";
    }

    public static AnimePostRequestBody getAnimePostRequestBody() {
        AnimePostRequestBody animePostRequestBody = new AnimePostRequestBody();
        animePostRequestBody.setName("Hajime no Ippo");
        return animePostRequestBody;
    }

    public static AnimePutRequestBody getAnimePutRequestBody() {
        AnimePutRequestBody animePutRequestBody = new AnimePutRequestBody();
        animePutRequestBody.setName("Hajime no Ippo Updated");
        return animePutRequestBody;
    }

}
