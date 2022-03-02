package br.dev.diego.springbootessentials.domain.mapper;

import br.dev.diego.springbootessentials.domain.AnimePostRequestBody;
import br.dev.diego.springbootessentials.domain.AnimePutRequestBody;
import br.dev.diego.springbootessentials.entities.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
