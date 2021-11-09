package pokemon.api.crud.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TipoPokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pokemon_id")
    private Pokemon pokemon;

    @Id
    @Column(name = "type_id")
    private Type type;

}
