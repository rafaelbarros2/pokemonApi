package pokemon.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pokemon.api.crud.model.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Optional<Pokemon> findPokemonByNum(String num);


    void deletePokemonByNum(String num);

}
