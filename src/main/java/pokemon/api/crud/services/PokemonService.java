package pokemon.api.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.model.Pokemon;
import pokemon.api.crud.repositories.PokemonRepository;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional(readOnly = true)
    public Page<PokemonDTO> findAllPaged(Pageable pageable) {
        Page<Pokemon> list = pokemonRepository.findAll(pageable);
        return list.map(x -> new PokemonDTO(x));
    }
}
