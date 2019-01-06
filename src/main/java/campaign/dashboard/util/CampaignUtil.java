package campaign.dashboard.util;

import campaign.dashboard.dto.CampaignDTO;
import campaign.dashboard.entity.Campaign;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CampaignUtil {

    public static CampaignDTO fromEntity(Campaign campaign) {

        CampaignDTO campaignDTO = new CampaignDTO();

        campaignDTO.setId(campaign.getId().toString());
        campaignDTO.setCampaignUniqueId(campaign.getCampaignUniqueId());
        campaignDTO.setName(campaign.getName());
        campaignDTO.setGoal(campaign.getGoal());
        campaignDTO.setStatus(campaign.getStatus().toString());
        campaignDTO.setTotalBudget(campaign.getTotalBudget().toString());

        return campaignDTO;
    }

    public static List<CampaignDTO> fromEntities(List<Campaign> campaigns) {

        List<CampaignDTO> result = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            CampaignDTO campaignDTO = new CampaignDTO();

            campaignDTO.setId(campaign.getId().toString());
            campaignDTO.setCampaignUniqueId(campaign.getCampaignUniqueId());
            campaignDTO.setName(campaign.getName());
            campaignDTO.setGoal(campaign.getGoal());
            campaignDTO.setStatus(campaign.getStatus().toString());
            campaignDTO.setTotalBudget(campaign.getTotalBudget().toString());

            result.add(campaignDTO);
        }

        return result;
    }

    public static Campaign fromDTO(CampaignDTO campaignDTO) {

        Campaign entity = new Campaign();

        String id = campaignDTO.getId();

        if (id != null) {
            entity.setId(Long.valueOf(id));
        }

        entity.setCampaignUniqueId(campaignDTO.getCampaignUniqueId());
        entity.setName(campaignDTO.getName());
        entity.setGoal(campaignDTO.getGoal());
        entity.setStatus(Campaign.Status.valueOf(campaignDTO.getStatus()));
        entity.setTotalBudget(new BigDecimal(campaignDTO.getTotalBudget()));

        return entity;
    }

    public static List<Campaign> fromDTOs(List<CampaignDTO> campaignsDTO) {

        List<Campaign> result = new ArrayList<>();

        for (CampaignDTO campaignDTO : campaignsDTO) {
            Campaign entity = new Campaign();

            if (campaignDTO.getId() != null) {
                entity.setId(Long.valueOf(campaignDTO.getId()));
            }

            entity.setCampaignUniqueId(campaignDTO.getCampaignUniqueId());
            entity.setName(campaignDTO.getName());
            entity.setGoal(campaignDTO.getGoal());
            entity.setStatus(Campaign.Status.valueOf(campaignDTO.getStatus()));
            entity.setTotalBudget(new BigDecimal(campaignDTO.getTotalBudget()));

            result.add(entity);
        }

        return result;
    }

    public static Campaign merge(Campaign campaign, CampaignDTO campaignDTO) {

        campaign.setId(Long.valueOf(campaignDTO.getId()));

        if(campaignDTO.getCampaignUniqueId() != null && !campaignDTO.getCampaignUniqueId().trim().isEmpty()){
            campaign.setCampaignUniqueId(campaignDTO.getCampaignUniqueId());
        }

        if(campaignDTO.getName() != null && !campaignDTO.getName().trim().isEmpty()){
            campaign.setName(campaignDTO.getName());
        }

        if(campaignDTO.getGoal() != null && !campaignDTO.getGoal().trim().isEmpty()){
            campaign.setGoal(campaignDTO.getGoal());
        }

        if(campaignDTO.getStatus() != null && !campaignDTO.getStatus().trim().isEmpty()){
            campaign.setStatus(Campaign.Status.valueOf(campaignDTO.getStatus()));
        }

        if(campaignDTO.getTotalBudget() != null && !campaignDTO.getTotalBudget().trim().isEmpty()){
            campaign.setTotalBudget(new BigDecimal(campaignDTO.getTotalBudget()));
        }

        return campaign;
    }

}
