package br.dev.diego.springbootessentials;

import br.dev.diego.springbootessentials.entities.Anime;

public class AnimeMother {

    public static Anime getAnime() {
        Anime anime = new Anime();
        anime.setName("Hajime no Ippo");
        return anime;
    }

}
