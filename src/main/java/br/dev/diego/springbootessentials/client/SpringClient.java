package br.dev.diego.springbootessentials.client;

import br.dev.diego.springbootessentials.entities.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class SpringClient {

    public static final Logger LOG = LoggerFactory.getLogger(SpringClient.class);

    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        LOG.info(entity.toString());

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 3);
        LOG.info("ID :" + object.getId());
        LOG.info("Name :" + object.getName());

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        LOG.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        LOG.info(exchange.getBody().toString());

//        Anime newAnime = new Anime();
//        newAnime.setName("kingdom");
//        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", newAnime, Anime.class);
//        LOG.info("Saved anime {}", animeSaved);

        Anime samuraiChamploo = new Anime();
        samuraiChamploo.setName("Samurai Champloo");
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                Anime.class);
        LOG.info("Saved anime {}", samuraiChamplooSaved);

        Anime animeToBeUpdated = samuraiChamplooSaved.getBody();
        animeToBeUpdated.setName("Samurai Champloo 2");
        ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);
        LOG.info(samuraiChamplooUpdated.toString());

        ResponseEntity<Void> samuraiChamplooDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());
        LOG.info(samuraiChamplooDeleted.toString());

    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
