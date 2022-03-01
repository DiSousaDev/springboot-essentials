package br.dev.diego.springbootessentials.controller;

import br.dev.diego.springbootessentials.domain.Anime;
import br.dev.diego.springbootessentials.service.AnimeService;
import br.dev.diego.springbootessentials.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
public class AnimeController {

    private static final Logger LOG = LoggerFactory.getLogger(AnimeController.class);

    @Autowired
    private AnimeService animeService;

    @Autowired
    private DateUtil dateUtil;

    @GetMapping
    public List<Anime> list() {
        LOG.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return animeService.listAll();
    }


}
