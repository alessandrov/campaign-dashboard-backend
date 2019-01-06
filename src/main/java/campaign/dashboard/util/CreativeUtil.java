package campaign.dashboard.util;

import campaign.dashboard.entity.Creative;
import campaign.dashboard.repository.PlatformRepository;
import campaign.dashboard.dto.CreativeDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CreativeUtil {

    private static PlatformRepository platformRepository;

    @Autowired
    private PlatformRepository platformRepository0;

    @PostConstruct
    private void initStaticDao() {
        platformRepository = this.platformRepository0;
    }

    public static CreativeDTO fromEntity(Creative creative) {

        CreativeDTO creativeDTO = new CreativeDTO();

        creativeDTO.setId(creative.getId().toString());
        creativeDTO.setPlatformId(creative.getPlatform().getId().toString());
        creativeDTO.setDescription(creative.getDescription());
        creativeDTO.setHeader(creative.getHeader());
        creativeDTO.setHeader1(creative.getHeader1());
        creativeDTO.setHeader2(creative.getHeader2());
        creativeDTO.setImage(creative.getImage());
        creativeDTO.setUrl(creative.getUrl());

        return creativeDTO;
    }

    public static Creative fromDTO(CreativeDTO creativeDTO) {

        Creative entity = new Creative();

        String id = creativeDTO.getId();

        if (id != null) {
            entity.setId(Long.valueOf(id));
        }

        entity.setPlatform(platformRepository.findById(Long.valueOf(creativeDTO.getPlatformId())).get());
        entity.setHeader(creativeDTO.getHeader());
        entity.setHeader1(creativeDTO.getHeader1());
        entity.setHeader2(creativeDTO.getHeader2());
        entity.setDescription(creativeDTO.getDescription());
        entity.setImage(creativeDTO.getImage());
        entity.setUrl(creativeDTO.getUrl());

        return entity;
    }

}
