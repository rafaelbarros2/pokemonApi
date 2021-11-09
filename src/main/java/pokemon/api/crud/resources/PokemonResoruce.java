package pokemon.api.crud.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pokemon.api.crud.dto.PokemonDTO;
import pokemon.api.crud.services.PokemonService;

import java.net.URI;

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

    @GetMapping(value = "/{num}")
    public ResponseEntity<PokemonDTO> findByNum(@PathVariable String num) {
        PokemonDTO dto = service.findByNum(num);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> insert(@RequestBody PokemonDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{num}")
    public ResponseEntity<PokemonDTO> update(@PathVariable String num, @RequestBody PokemonDTO dto) {
        dto = service.update(num, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{num}")
    public ResponseEntity<Void> delete(@PathVariable String num) {
        service.delete(num);
        return ResponseEntity.noContent().build();
    }
}
