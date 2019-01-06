package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Gender implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        M, F
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Gender.Type type;

    public Gender(){
    }

    public Gender(Gender.Type type) {

        notNull(type, "Method called with null parameter (type)");

        this.type = type;
    }

}
