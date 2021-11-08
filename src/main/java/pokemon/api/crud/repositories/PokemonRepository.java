package pokemon.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pokemon.api.crud.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
