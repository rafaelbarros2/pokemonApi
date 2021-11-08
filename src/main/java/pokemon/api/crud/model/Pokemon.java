package pokemon.api.crud.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String num;

    private String name;

    @ManyToMany
    @JoinTable(name = "tb_pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    Set<Type> type = new HashSet<>();

    @OneToMany
    private List<NextEvolution> nextEvolutions;

    private List<PreEvolution> preEvolutions;
}
