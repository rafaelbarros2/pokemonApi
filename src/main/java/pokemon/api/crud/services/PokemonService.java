package pokemon.api.crud.services;

import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.model.Pokemon;
import pokemon.api.crud.model.Type;
import pokemon.api.crud.repositories.PokemonRepository;
import pokemon.api.crud.services.exceptions.DatabaseException;
import pokemon.api.crud.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
    public PokemonDTO findByNum(String num) {
        Optional<Pokemon> obj = pokemonRepository.findPokemonByNum(num);
        Pokemon entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
       return dozerBeanMapper.map(entity, PokemonDTO.class);

    }

    public PokemonDTO insert(PokemonDTO dto) {
        try {
            Pokemon entity = new Pokemon();
            entity.setName(dto.getName());
            entity.setPreEvolutions(dto.getPreEvolutions());
            entity.setType(dto.getType());
            entity.setNum(dto.getNum());
            entity.setNextEvolutions(dto.getNextEvolutions());
            entity = pokemonRepository.saveAndFlush(entity);
            return new PokemonDTO(entity);
        }catch (ConstraintViolationException e){
            throw new DatabaseException("Esse Pokemon já existe");
        }

    }

    @Transactional
    public PokemonDTO update(String num, PokemonDTO dto) {
        Optional<Pokemon> pokemon = pokemonRepository.findPokemonByNum(num);
        if( pokemon.isPresent()){
            Pokemon entity = pokemon.get();
            entity.setName(dto.getName());
            entity.setPreEvolutions(dto.getPreEvolutions());
            entity.setType(dto.getType());
            entity.setNum(dto.getNum());
            entity.setNextEvolutions(dto.getNextEvolutions());
            entity = pokemonRepository.save(entity);
            return new PokemonDTO(entity);
        }
         else {
            throw new ResourceNotFoundException("Num not found " + num);
        }
    }

}