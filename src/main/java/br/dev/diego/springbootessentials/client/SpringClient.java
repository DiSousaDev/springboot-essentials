package br.dev.diego.springbootessentials.client;

import br.dev.diego.springbootessentials.entities.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringClient {

        public static final Logger LOG = LoggerFactory.getLogger(SpringClient.class);

    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        LOG.info(entity.toString());

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 3);
        LOG.info("ID :" + object.getId());
        LOG.info("Name :" + object.getName());
    }

}
