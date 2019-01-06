package campaign.dashboard.util;

import campaign.dashboard.dto.PlatformDTO;
import campaign.dashboard.entity.Platform;
import campaign.dashboard.repository.CampaignRepository;
import campaign.dashboard.repository.PlatformRepository;
import campaign.dashboard.repository.PlatformTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class PlatformUtil {

    private static final Logger logger = LoggerFactory.getLogger(PlatformUtil.class);

    private static PlatformRepository platformRepository;

    private static CampaignRepository campaignRepository;

    private static PlatformTypeRepository platformTypeRepository;

    @Autowired
    private PlatformRepository platformRepository0;

    @Autowired
    private PlatformTypeRepository platformTypeRepository0;

    @Autowired
    private CampaignRepository campaignRepository0;

    @PostConstruct
    private void initStaticDao () {
        campaignRepository = this.campaignRepository0;
        platformRepository = this.platformRepository0;
        platformTypeRepository = this.platformTypeRepository0;
    }

    public static PlatformDTO fromEntity(Platform platform) {

        PlatformDTO platformDTO = new PlatformDTO();

        platformDTO.setId(platform.getId().toString());
        platformDTO.setCampaignId(platform.getCampaign().getId().toString());
        platformDTO.setPlatformType(platform.getPlatformType().getName());
        platformDTO.setPlatformTypeId(platform.getPlatformType().getId().toString());
        platformDTO.setTotalBudget(platform.getTotalBudget().toString());
        platformDTO.setRemainingBudget(platform.getRemainingBudget().toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedStartDate = platform.getStartDate().format(formatter);
        platformDTO.setStartDate(formattedStartDate);

        String formattedEndDate = platform.getEndDate().format(formatter);
        platformDTO.setEndDate(formattedEndDate);

        platformDTO.setStatus(platform.getStatus().toString());

        return platformDTO;
    }

    public static List<PlatformDTO> fromEntities(List<Platform> platforms) {

        List<PlatformDTO> result = new ArrayList<>();

        for (Platform platform : platforms) {
            PlatformDTO platformDTO = new PlatformDTO();

            platformDTO.setId(platform.getId().toString());
            platformDTO.setCampaignId(platform.getCampaign().getId().toString());
            platformDTO.setPlatformType(platform.getPlatformType().getName());
            platformDTO.setPlatformTypeId(platform.getPlatformType().getId().toString());
            platformDTO.setTotalBudget(platform.getTotalBudget().toString());
            platformDTO.setRemainingBudget(platform.getRemainingBudget().toString());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedStartDate = platform.getStartDate().format(formatter);
            platformDTO.setStartDate(formattedStartDate);

            String formattedEndDate = platform.getEndDate().format(formatter);
            platformDTO.setEndDate(formattedEndDate);

            platformDTO.setStatus(platform.getStatus().toString());

            result.add(platformDTO);
        }

        return result;
    }

    public static Platform fromDTO(PlatformDTO platformDTO) {

        Platform entity = new Platform();

        String id = platformDTO.getId();

        if (id != null) {
            entity.setId(Long.valueOf(id));
        }

        entity.setCampaign(campaignRepository.findById(Long.valueOf(platformDTO.getCampaignId())).get());
        entity.setPlatformType(platformTypeRepository.findById(Long.valueOf(platformDTO.getPlatformTypeId())).get());
        entity.setTotalBudget(new BigDecimal(platformDTO.getTotalBudget()));
        entity.setRemainingBudget(new BigDecimal(platformDTO.getRemainingBudget()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ITALIAN);
        LocalDateTime startDate = LocalDateTime.parse(platformDTO.getStartDate(), formatter);

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ITALIAN);
        LocalDateTime endDate = LocalDateTime.parse(platformDTO.getEndDate(), formatter);

        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
        entity.setStatus(Platform.Status.valueOf(platformDTO.getStatus()));

        return entity;
    }

    public static Platform merge(Platform platform, PlatformDTO platformDTO) {

        //most of those are not null by definition, defensive coding

        platform.setId(Long.valueOf(platformDTO.getId()));

        if(platformDTO.getCampaignId() != null && !platformDTO.getCampaignId().trim().isEmpty()){
            platform.setCampaign(campaignRepository.findById(Long.valueOf(platformDTO.getCampaignId())).get());
        }

        if(platformDTO.getPlatformTypeId() != null && !platformDTO.getPlatformTypeId().trim().isEmpty()){
            platform.setPlatformType(platformTypeRepository.findById(Long.valueOf(platformDTO.getPlatformTypeId())).get());
        }

        if(platformDTO.getEndDate() != null && !platformDTO.getEndDate().trim().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);

            LocalDateTime endDate = LocalDateTime.parse(platformDTO.getEndDate(), formatter);

            platform.setEndDate(endDate);
        }

        if(platformDTO.getStartDate() != null && !platformDTO.getStartDate().trim().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);

            LocalDateTime startDate = LocalDateTime.parse(platformDTO.getStartDate(), formatter);

            platform.setStartDate(startDate);
        }

        if(platformDTO.getRemainingBudget() != null && !platformDTO.getRemainingBudget().trim().isEmpty()){
            platform.setRemainingBudget(new BigDecimal(platformDTO.getRemainingBudget()));
        }

        if(platformDTO.getTotalBudget() != null && !platformDTO.getTotalBudget().trim().isEmpty()){
            platform.setTotalBudget(new BigDecimal(platformDTO.getTotalBudget()));
        }

        if(platformDTO.getStatus() != null && !platformDTO.getStatus().trim().isEmpty()){
            platform.setStatus(Platform.Status.valueOf(platformDTO.getStatus()));
        }

        return platform;
    }

}
