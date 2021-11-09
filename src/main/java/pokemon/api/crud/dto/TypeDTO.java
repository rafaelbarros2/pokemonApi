package pokemon.api.crud.dto;

import lombok.Data;
import pokemon.api.crud.model.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class TypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public TypeDTO(Type type) {
        this.id = type.getId();
        this.name = type.getName();
    }

    public TypeDTO() {
    }
}
