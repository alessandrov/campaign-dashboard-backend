package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Platform implements Serializable {

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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "platform_type_id", nullable = false)
    private PlatformType platformType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Platform.Status status;

    @NotNull
    @Column(name = "total_budget", scale = 2, nullable = false)
    private BigDecimal totalBudget;

    @NotNull
    @Column(name = "remaining_budget", scale = 2, nullable = false)
    private BigDecimal remainingBudget;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Platform(){
    }

    public Platform(Campaign campaign, PlatformType platformType, Platform.Status status, BigDecimal totalBudget, BigDecimal remainingBudget,
                    LocalDateTime startDate) {

        notNull(campaign, "Method called with null parameter (campaign)");
        notNull(platformType, "Method called with null parameter (platformType)");
        notNull(status, "Method called with null parameter (status)");
        notNull(totalBudget, "Method called with null parameter (totalBudget)");
        notNull(remainingBudget, "Method called with null parameter (remainingBudget)");
        notNull(startDate, "Method called with null parameter (startDate)");

        this.campaign = campaign;
        this.platformType = platformType;
        this.status = status;
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
        this.startDate = startDate;
    }

}
