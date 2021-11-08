package pokemon.api.crud.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.services.PokemonService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonResoruce {

    @Autowired
    private PokemonService service;

    @GetMapping
    public ResponseEntity<Page<PokemonDTO>> findAll(Pageable pageable) {
        Page<PokemonDTO> list = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(list);
    }



}
