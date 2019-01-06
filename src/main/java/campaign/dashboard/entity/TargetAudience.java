package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class TargetAudience implements Serializable {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "target_audience_language", joinColumns = @JoinColumn(name = "target_audience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
    private Set<Language> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "target_audience_gender", joinColumns = @JoinColumn(name = "target_audience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id", referencedColumnName = "id"))
    private Set<Gender> genders;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "target_audience_location", joinColumns = @JoinColumn(name = "target_audience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
    private Set<Location> locations;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "target_audience_interest", joinColumns = @JoinColumn(name = "target_audience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"))
    private Set<Interest> interests;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "target_audience_keyword", joinColumns = @JoinColumn(name = "target_audience_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id"))
    private Set<Keyword> keywords;

    @OneToOne(fetch = FetchType.EAGER)
    private AgeRange ageRange;

    public TargetAudience(){
    }

    public TargetAudience(Platform platform) {

        notNull(platform, "Method called with null parameter (platform)");

        this.platform = platform;
    }

}
