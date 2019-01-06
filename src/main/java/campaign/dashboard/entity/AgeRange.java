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
public class AgeRange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @NotNull
    @Column(name = "low_limit", nullable = false)
    private int lowLimit;

    @NotNull
    @Column(name = "high_limit", nullable = false)
    private int highLimit;

    public AgeRange(){

    }

    public AgeRange(int lowLimit, int highLimit) {

        notNull(lowLimit, "Method called with null parameter (lowLimit)");
        notNull(highLimit, "Method called with null parameter (highLimit)");

        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
    }

}
