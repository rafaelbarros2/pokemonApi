package pokemon.api.crud.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class NextEvelotionPokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pokemon_id")
    private Pokemon pokemon;

    @Id
    @Column(name = "nextEvolution_id")
    private NextEvolution nextEvolution;

}
