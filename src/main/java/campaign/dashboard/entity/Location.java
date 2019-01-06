package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Location(){
    }

    public Location(String name) {

        notNull(name, "Method called with null parameter (name)");

        this.name = name;
    }

}
