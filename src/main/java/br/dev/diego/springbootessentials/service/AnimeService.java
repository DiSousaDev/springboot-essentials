package br.dev.diego.springbootessentials.service;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.domain.mapper.AnimeMapper;
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

    @Autowired
    private AnimeMapper animeMapper;

    public List<Anime> listAll() {
        return repository.findAll();
    }

    public List<Anime> findByName(String name) {
        return repository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }


    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return repository.save(animeMapper.toAnime(animePostRequestBody));
    }

    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(AnimePutRequestBody animePutRequestBody) {
        findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        repository.save(animeMapper.toAnime(animePutRequestBody));
    }
}
