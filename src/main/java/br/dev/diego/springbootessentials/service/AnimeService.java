package br.dev.diego.springbootessentials.service;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.domain.mapper.AnimeMapper;
import br.dev.diego.springbootessentials.entities.Anime;
import br.dev.diego.springbootessentials.repository.AnimeRepository;
import br.dev.diego.springbootessentials.service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository repository;

    @Autowired
    private AnimeMapper animeMapper;

    @Transactional(readOnly = true)
    public Page<Anime> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Anime> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Anime> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Anime findByIdOrThrowBadRequestException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return repository.save(animeMapper.toAnime(animePostRequestBody));
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public void update(AnimePutRequestBody animePutRequestBody) {
        findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        repository.save(animeMapper.toAnime(animePutRequestBody));
    }
}
