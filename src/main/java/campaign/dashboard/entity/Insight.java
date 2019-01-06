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

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Insight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @NotNull
    @Column(name = "impressions", nullable = false)
    private int impressions;

    @NotNull
    @Column(name = "clicks", nullable = false)
    private int clicks;

    @Column(name = "nanos_score", scale = 2)
    private BigDecimal nanosScore;

    @Column(name = "website_visits")
    private BigDecimal websiteVisits;

    @NotNull
    @Column(name = "cost_per_click", scale = 2, nullable = false)
    private BigDecimal costPerClick;

    @NotNull
    @Column(name = "click_through_rate", scale = 5, nullable = false)
    private BigDecimal clickThroughRate;

    @NotNull
    @Column(name = "advanced_kpi_1", precision = 11, scale = 5, nullable = false)
    private BigDecimal advancedKpi1;

    @Column(name = "advanced_kpi_2", precision = 11, scale = 5)
    private BigDecimal advancedKpi2;

    public Insight(){
    }

    public Insight(Platform platform, int impressions, int clicks, BigDecimal costPerClick,
                   BigDecimal clickThroughRate, BigDecimal advancedKpi1) {

        notNull(platform, "Method called with null parameter (platform)");
        notNull(impressions, "Method called with null parameter (impressions)");
        notNull(clicks, "Method called with null parameter (clicks)");
        notNull(costPerClick, "Method called with null parameter (costPerClick)");
        notNull(clickThroughRate, "Method called with null parameter (clickThroughRate)");
        notNull(advancedKpi1, "Method called with null parameter (advancedKpi1)");

        this.platform = platform;
        this.impressions = impressions;
        this.clicks = clicks;
        this.costPerClick = costPerClick;
        this.clickThroughRate = clickThroughRate;
        this.advancedKpi1 = advancedKpi1;
    }

}
