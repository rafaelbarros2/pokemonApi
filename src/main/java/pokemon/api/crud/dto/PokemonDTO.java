package pokemon.api.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pokemon.api.crud.model.NextEvolution;
import pokemon.api.crud.model.Pokemon;
import pokemon.api.crud.model.PreEvolution;
import pokemon.api.crud.model.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PokemonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;

    private String num;

    private String name;

   private List<Type> type;

    private List<NextEvolution> nextEvolutions;

    private List<PreEvolution> preEvolutions;

    public PokemonDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.num = pokemon.getNum();
        this.name = pokemon.getName();
        this.type = pokemon.getType();
        this.nextEvolutions = pokemon.getNextEvolutions();
        this.preEvolutions = pokemon.getPreEvolutions();
    }

    public PokemonDTO() {
    }
}
