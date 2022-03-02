package br.dev.diego.springbootessentials.domain;

import javax.validation.constraints.NotEmpty;

public class AnimePostRequestBody {

    @NotEmpty(message = "The name cannot be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
