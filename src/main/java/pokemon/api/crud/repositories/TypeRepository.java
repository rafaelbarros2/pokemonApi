package pokemon.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pokemon.api.crud.model.Type;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {


    Optional<Type> findByNameIgnoreCase(String name);
}
