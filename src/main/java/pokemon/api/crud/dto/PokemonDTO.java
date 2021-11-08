package pokemon.api.crud.dto;

import lombok.Data;
import pokemon.api.crud.model.NextEvolution;
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

    private Long id;

    private String num;

    private String name;

   private List<TypeDTO> type;

    private List<NextEvolutionDTO> nextEvolutions;

    private List<PreEvolutionDTO> preEvolutions;
}
