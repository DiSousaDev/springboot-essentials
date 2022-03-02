package br.dev.diego.springbootessentials.service;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.entities.Anime;
import br.dev.diego.springbootessentials.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository repository;

    public List<Anime> listAll() {
        return repository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }


    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = new Anime();
        anime.setName(animePostRequestBody.getName());
        anime = repository.save(anime);
        return anime;
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(AnimePutRequestBody animePutRequestBody) {
        findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = new Anime();
        anime.setId(animePutRequestBody.getId());
        anime.setName(animePutRequestBody.getName());
        repository.save(anime);
    }
}
