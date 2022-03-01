package br.dev.diego.springbootessentials.service;

import br.dev.diego.springbootessentials.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    public List<Anime> listAll() {
        return List.of(new Anime(1L, "DBZ"), new Anime(2L, "Berserk"));
    }

}
