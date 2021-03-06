package pokemon.api.crud.services;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.model.Pokemon;
import pokemon.api.crud.model.Type;
import pokemon.api.crud.repositories.PokemonRepository;
import pokemon.api.crud.repositories.TypeRepository;
import pokemon.api.crud.services.exceptions.DatabaseException;
import pokemon.api.crud.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class PokemonService {


    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Transactional(readOnly = true)
    public Page<PokemonDTO> findAllPaged(Pageable pageable) {
        Mapper mapper = new  DozerBeanMapper();
        Page<Pokemon> list = pokemonRepository.findAll(pageable);
        return list.map(x -> mapper.map(x , PokemonDTO.class));
    }

    @Transactional(readOnly = true)
    public PokemonDTO findByNum(String num) {
        Optional<Pokemon> obj = pokemonRepository.findPokemonByNum(num);
        Pokemon entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
       return dozerBeanMapper.map(entity, PokemonDTO.class);

    }

    @Transactional(readOnly = true)
    public PokemonDTO findPokemonByTipo(String type) {
        Optional<Type> typeObj = typeRepository.findByNameIgnoreCase(type);
        Optional<Pokemon> obj = pokemonRepository.findPokemonByType(typeObj.get());
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
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Esse Pokemon j?? existe");
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
    public void delete(String num) {
        Optional<Pokemon> pokemon = pokemonRepository.findPokemonByNum(num);
        if( pokemon.isPresent()){
            pokemonRepository.deleteById(pokemon.get().getId());
        }
        else {
            throw new ResourceNotFoundException("Num not found " + num);
        }
    }
}