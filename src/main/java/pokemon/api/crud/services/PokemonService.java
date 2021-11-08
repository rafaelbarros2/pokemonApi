package pokemon.api.crud.services;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.model.Pokemon;
import pokemon.api.crud.repositories.PokemonRepository;
import pokemon.api.crud.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class PokemonService {


    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional(readOnly = true)
    public Page<PokemonDTO> findAllPaged(Pageable pageable) {
        Page<Pokemon> list = pokemonRepository.findAll(pageable);
        return list.map(x -> new PokemonDTO(x));
    }

    @Transactional(readOnly = true)
    public PokemonDTO findById(Long id) {
        Optional<Pokemon> obj = pokemonRepository.findById(id);
        Pokemon entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PokemonDTO(entity);
    }

    public PokemonDTO insert(PokemonDTO dto) {
        Pokemon entity = new Pokemon();
        entity.setName(dto.getName());
        entity.setPreEvolutions(dto.getPreEvolutions());
        entity.setType(dto.getType());
        entity.setNum(dto.getNum());
        entity.setNextEvolutions(dto.getNextEvolutions());
        entity = pokemonRepository.saveAndFlush(entity);
        return new PokemonDTO(entity);

    }
}