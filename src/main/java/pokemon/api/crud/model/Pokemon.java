package pokemon.api.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "pokemon")
public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String num;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Type> type;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<NextEvolution> nextEvolutions;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<PreEvolution> preEvolutions;
}
