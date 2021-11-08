package pokemon.api.crud.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class PreEvolutionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}

