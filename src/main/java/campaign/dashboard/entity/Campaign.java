package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status {
        SCHEDULED, DELIVERING, ENDED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @NotBlank
    @Column(name = "campaign_unique_id", unique = true, nullable = false)
    private String campaignUniqueId;

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "goal", nullable = false)
    private String goal;

    @NotNull
    @Column(name = "total_budget", scale = 2, nullable = false)
    private BigDecimal totalBudget;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Campaign.Status status;

    public Campaign(){
    }

    public Campaign(String campaignUniqueId, String name, String goal, BigDecimal totalBudget, Campaign.Status status) {

        notNull(campaignUniqueId, "Method called with null parameter (application)");
        notNull(name, "Method called with null parameter (name)");
        notNull(goal, "Method called with null parameter (name)");
        notNull(totalBudget, "Method called with null parameter (name)");
        notNull(status, "Method called with null parameter (name)");

        this.campaignUniqueId = campaignUniqueId;
        this.name = name;
        this.goal = goal;
        this.totalBudget = totalBudget;
        this.status = status;
    }

}
