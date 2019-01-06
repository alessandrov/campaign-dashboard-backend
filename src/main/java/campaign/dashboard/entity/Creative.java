package campaign.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static org.springframework.util.Assert.notNull;

@Entity
@Getter
@Setter
public class Creative implements Serializable {

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

    @Column(name = "header")
    private String header;

    @Column(name = "header_1")
    private String header1;

    @Column(name = "header_2")
    private String header2;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "url", nullable = false)
    private String url;

    @NotBlank
    @Column(name = "image", nullable = false)
    private String image;

    public Creative(){
    }

    public Creative(Platform platform, String description, String url, String image) {

        notNull(platform, "Method called with null parameter (platform)");
        notNull(description, "Method called with null parameter (description)");
        notNull(url, "Method called with null parameter (url)");
        notNull(image, "Method called with null parameter (image)");

        this.platform = platform;
        this.description = description;
        this.url = url;
        this.image = image;
    }

}
